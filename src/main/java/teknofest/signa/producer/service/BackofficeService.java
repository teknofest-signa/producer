package teknofest.signa.producer.service;

import static teknofest.signa.producer.constants.ErrorConstants.ADMIN_NOT_FOUND;
import static teknofest.signa.producer.constants.ErrorConstants.FAILED_TO_UPLOAD_PHOTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teknofest.signa.producer.enums.Status;
import teknofest.signa.producer.handler.exception.ApplicationException;
import teknofest.signa.producer.handler.exception.ResourceNotFoundException;
import teknofest.signa.producer.model.dto.backoffice.InfoResponse;
import teknofest.signa.producer.model.dto.transaction.TransactionInfo;
import teknofest.signa.producer.model.entity.Admin;
import teknofest.signa.producer.model.entity.Transaction;
import teknofest.signa.producer.repository.AdminRepository;
import teknofest.signa.producer.repository.TransactionRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeService {

    private final AdminRepository adminRepository;
    private final TransactionRepository transactionRepository;

    public InfoResponse getInfo(String email) {
        Admin admin = adminRepository.findByEmailAndStatus(email, Status.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException(ADMIN_NOT_FOUND));

        return InfoResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .status(admin.getStatus())
                .role(admin.getRole())
                .profilePhoto(admin.getProfilePhoto())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }

    public void uploadProfilePhoto(String email, MultipartFile multipartFile) {
        Admin admin = adminRepository.findByEmailAndStatus(email, Status.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException(ADMIN_NOT_FOUND));

        if (multipartFile == null || multipartFile.isEmpty()) {
            admin.setProfilePhoto(null);
            adminRepository.save(admin);
            return;
        }

        try {
            admin.setProfilePhoto(multipartFile.getBytes());
            adminRepository.save(admin);
        } catch (Exception exception) {
            throw new ApplicationException(FAILED_TO_UPLOAD_PHOTO);
        }
    }

    public List<TransactionInfo> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::toTransactionInfo)
                .toList();
    }

    private TransactionInfo toTransactionInfo(Transaction transaction) {
        return TransactionInfo.builder()
                .id(transaction.getId())
                .fraudScore(transaction.getFraudScore())
                .transactionStatus(transaction.getTransactionStatus())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .toAccountId(transaction.getToAccountId())
                .fromAccountId(transaction.getFromAccountId())
                .referenceId(transaction.getReferenceId())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }
}
