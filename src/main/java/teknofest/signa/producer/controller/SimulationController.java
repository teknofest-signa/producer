package teknofest.signa.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.simulation.SimulateTransactionRequest;
import teknofest.signa.producer.model.dto.simulation.SimulateTransactionResponse;
import teknofest.signa.producer.service.SimulationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    @PostMapping("/transactions")
    public SimulateTransactionResponse simulateTransaction(SimulateTransactionRequest simulateTransactionRequest) {
        return simulationService.simulateTransaction(simulateTransactionRequest);
    }
}
