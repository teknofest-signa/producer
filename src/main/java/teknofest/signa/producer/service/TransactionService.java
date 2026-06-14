package teknofest.signa.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.dto.transaction.CreateTransactionRequest;
import teknofest.signa.producer.model.dto.transaction.TransactionResponse;
import teknofest.signa.producer.repository.AccountRepository;
import teknofest.signa.producer.repository.CardRepository;
import teknofest.signa.producer.repository.TransactionRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) {
        return null;
    }
}
