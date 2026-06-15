package teknofest.signa.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.dto.simulation.SimulateTransactionRequest;
import teknofest.signa.producer.model.dto.simulation.SimulateTransactionResponse;
import teknofest.signa.producer.repository.SimulationRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulationService {

    private final SimulationRepository simulationRepository;

    public SimulateTransactionResponse simulateTransaction(SimulateTransactionRequest simulateTransactionRequest) {
        return null;
    }
}
