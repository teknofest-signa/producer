package teknofest.signa.producer.service;

import static teknofest.signa.producer.constants.ErrorConstants.ACCOUNT_NOT_FOUND;
import static teknofest.signa.producer.constants.ErrorConstants.CARD_NOT_FOUND;
import static teknofest.signa.producer.constants.ErrorConstants.INSUFFICIENT_BALANCE;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.enums.TransactionMethod;
import teknofest.signa.producer.enums.TransactionStatus;
import teknofest.signa.producer.enums.TransactionType;
import teknofest.signa.producer.handler.exception.ApplicationException;
import teknofest.signa.producer.handler.exception.ResourceNotFoundException;
import teknofest.signa.producer.model.dto.transaction.CreateTransactionRequest;
import teknofest.signa.producer.model.dto.transaction.TransactionInfo;
import teknofest.signa.producer.model.entity.Account;
import teknofest.signa.producer.model.entity.Card;
import teknofest.signa.producer.model.entity.Transaction;
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

    public TransactionInfo createTransaction(CreateTransactionRequest createTransactionRequest) {
        if (TransactionMethod.CARD_TO_CARD.equals(createTransactionRequest.getTransactionMethod())) {
            Card toCard = cardRepository.findById(createTransactionRequest.getToCardId())
                    .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
            Card fromCard = cardRepository.findById(createTransactionRequest.getFromCardId())
                    .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));

            Account toAccount = accountRepository.findById(toCard.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND));
            Account fromAccount = accountRepository.findById(fromCard.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND));

            if (fromAccount.getBalance().compareTo(createTransactionRequest.getAmount()) < 0) {
                throw new ApplicationException(INSUFFICIENT_BALANCE);
            }

            toAccount.setBalance(toAccount.getBalance().add(createTransactionRequest.getAmount()));
            fromAccount.setBalance(fromAccount.getBalance().subtract(createTransactionRequest.getAmount()));

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            Transaction transaction = Transaction.builder()
                    .senderId(fromAccount.getId())
                    .receiverId(toAccount.getId())
                    .amount(createTransactionRequest.getAmount())
                    .currency(createTransactionRequest.getCurrency())
                    .transactionType(TransactionType.TRANSFER)
                    .transactionStatus(TransactionStatus.SUCCESS)
                    .referenceId(UUID.randomUUID().toString())
                    .build();

            transactionRepository.save(transaction);
        }
        return TransactionInfo.builder()
                .score(ThreadLocalRandom.current().nextInt(101))
                .build();
    }
}
