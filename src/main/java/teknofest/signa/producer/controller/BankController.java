package teknofest.signa.producer.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.bank.BankInfo;
import teknofest.signa.producer.model.dto.bank.CreateBankRequest;
import teknofest.signa.producer.service.BankService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banks")
public class BankController {

    private final BankService bankService;

    @PostMapping
    public void createBank(@Valid @RequestBody CreateBankRequest createBankRequest) {
        bankService.createBank(createBankRequest);
    }

    @GetMapping
    public List<BankInfo> getAllBanks() {
        return bankService.getAllBanks();
    }
}
