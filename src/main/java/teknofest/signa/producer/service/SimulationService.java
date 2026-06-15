package teknofest.signa.producer.service;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.enums.TransactionFraudStatus;
import teknofest.signa.producer.enums.SimulationType;
import teknofest.signa.producer.model.dto.simulation.SimulateTransactionRequest;
import teknofest.signa.producer.model.dto.simulation.SimulateTransactionResponse;
import teknofest.signa.producer.model.entity.Simulation;
import teknofest.signa.producer.repository.SimulationRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulationService {

    private final SimulationRepository simulationRepository;

    public SimulateTransactionResponse simulateTransaction(SimulateTransactionRequest simulateTransactionRequest) {
        Simulation simulation = Simulation.builder()
                .simulationType(SimulationType.TRANSACTION)
                .amount(simulateTransactionRequest.getAmount())
                .currency(simulateTransactionRequest.getCurrency())
                .isNewBeneficiary(simulateTransactionRequest.isNewBeneficiary())
                .toAccountId(simulateTransactionRequest.getToAccountId())
                .fromAccountId(simulateTransactionRequest.getFromAccountId())
                .transactionChannel(simulateTransactionRequest.getTransactionChannel())
                .isCrossBorderTransaction(simulateTransactionRequest.isCrossBorderTransaction())
                .transactionTime(simulateTransactionRequest.getTransactionTime())
                .build();

        calculateFraud(simulation);

        simulationRepository.save(simulation);

        return SimulateTransactionResponse.builder()
                .transactionFraudScore(simulation.getTransactionFraudScore())
                .transactionFraudStatus(simulation.getTransactionFraudStatus())
                .build();
    }

    private void calculateFraud(Simulation simulation) {
        //TODO: a bit real calculation

        simulation.setTransactionFraudScore(BigDecimal.ONE);
        simulation.setTransactionFraudStatus(TransactionFraudStatus.BLOCK);
    }
}
