package teknofest.signa.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.transaction.CreateTransactionRequest;
import teknofest.signa.producer.model.dto.transaction.TransactionResponse;
import teknofest.signa.producer.service.TransactionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mobile-app")
public class MobileAppController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public TransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) {
        return transactionService.createTransaction(createTransactionRequest);
    }
}
