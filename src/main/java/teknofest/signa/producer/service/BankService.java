package teknofest.signa.producer.service;

import static teknofest.signa.producer.constants.ErrorConstants.BANK_NOT_FOUND;
import static teknofest.signa.producer.constants.ErrorConstants.FAILED_TO_UPLOAD_PHOTO;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teknofest.signa.producer.handler.exception.ApplicationException;
import teknofest.signa.producer.handler.exception.ResourceNotFoundException;
import teknofest.signa.producer.model.dto.bank.BankInfo;
import teknofest.signa.producer.model.dto.bank.CreateBankRequest;
import teknofest.signa.producer.model.entity.Bank;
import teknofest.signa.producer.repository.BankRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public void createBank(CreateBankRequest createBankRequest) {
        Bank bank = Bank.builder()
                .name(createBankRequest.getName())
                .build();
        bankRepository.save(bank);
    }

    public List<BankInfo> getAllBanks() {
        return bankRepository.findAll()
                .stream()
                .map(this::toBankInfo)
                .toList();
    }

    public void uploadLogo(UUID id, MultipartFile multipartFile) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BANK_NOT_FOUND));

        if (multipartFile == null || multipartFile.isEmpty()) {
            bank.setLogo(null);
            bankRepository.save(bank);
            return;
        }

        try {
            bank.setLogo(multipartFile.getBytes());
            bankRepository.save(bank);
        } catch (Exception exception) {
            throw new ApplicationException(FAILED_TO_UPLOAD_PHOTO);
        }
    }

    private BankInfo toBankInfo(Bank bank) {
        return BankInfo.builder()
                .id(bank.getId())
                .name(bank.getName())
                .createdAt(bank.getCreatedAt())
                .updatedAt(bank.getUpdatedAt())
                .build();
    }
}
