package teknofest.signa.producer.service;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.enums.SimulationType;
import teknofest.signa.producer.enums.TransactionFraudStatus;
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
        Simulation simulation = buildSimulation(simulateTransactionRequest);

        calculateFraud(simulation);

        simulationRepository.save(simulation);

        log.info("Transaction simulation completed. Score: {}, Status: {}", simulation.getTransactionFraudScore(), simulation.getTransactionFraudStatus());

        return SimulateTransactionResponse.builder()
                .transactionFraudScore(simulation.getTransactionFraudScore())
                .transactionFraudStatus(simulation.getTransactionFraudStatus())
                .build();
    }

    private Simulation buildSimulation(SimulateTransactionRequest simulateTransactionRequest) {
        return Simulation.builder()
                .simulationType(SimulationType.TRANSACTION)
                .fromAccountId(simulateTransactionRequest.getFromAccountId())
                .toAccountId(simulateTransactionRequest.getToAccountId())
                .amount(simulateTransactionRequest.getAmount())
                .currency(simulateTransactionRequest.getCurrency())
                .transactionChannel(simulateTransactionRequest.getTransactionChannel())
                .transactionTime(simulateTransactionRequest.getTransactionTime())
                .isNewBeneficiary(simulateTransactionRequest.isNewBeneficiary())
                .isCrossBorderTransaction(simulateTransactionRequest.isCrossBorderTransaction())
                .build();
    }

    private void calculateFraud(Simulation simulation) {
        BigDecimal score = BigDecimal.ZERO;

        if (simulation.getAmount() != null && simulation.getAmount().compareTo(BigDecimal.valueOf(10_000)) > 0) {
            score = score.add(BigDecimal.valueOf(0.40));
        }

        if (simulation.isCrossBorderTransaction()) {
            score = score.add(BigDecimal.valueOf(0.30));
        }

        if (simulation.isNewBeneficiary()) {
            score = score.add(BigDecimal.valueOf(0.20));
        }

        TransactionFraudStatus status;

        if (score.compareTo(BigDecimal.valueOf(0.80)) >= 0) {
            status = TransactionFraudStatus.BLOCK;
        } else if (score.compareTo(BigDecimal.valueOf(0.50)) >= 0) {
            status = TransactionFraudStatus.REVIEW;
        } else {
            status = TransactionFraudStatus.APPROVE;
        }

        simulation.setTransactionFraudScore(score);
        simulation.setTransactionFraudStatus(status);
    }
}
