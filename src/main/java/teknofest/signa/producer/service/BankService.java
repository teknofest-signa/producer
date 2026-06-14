package teknofest.signa.producer.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    private BankInfo toBankInfo(Bank bank) {
        return BankInfo.builder()
                .id(bank.getId())
                .name(bank.getName())
                .createdAt(bank.getCreatedAt())
                .updatedAt(bank.getUpdatedAt())
                .build();
    }
}
