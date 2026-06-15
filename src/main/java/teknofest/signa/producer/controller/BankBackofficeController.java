package teknofest.signa.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.transaction.CreateTransactionRequest;
import teknofest.signa.producer.model.dto.transaction.TransactionInfo;
import teknofest.signa.producer.service.TransactionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-backoffice")
public class BankBackofficeController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public TransactionInfo createTransaction(CreateTransactionRequest createTransactionRequest) {
        return transactionService.createTransaction(createTransactionRequest);
    }
}
