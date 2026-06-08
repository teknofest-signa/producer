package teknofest.signa.producer.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.dto.CustomerInfo;
import teknofest.signa.producer.service.VerificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VerificationController {

    private final VerificationService verificationService;

    @GetMapping("/verify")
    public List<CustomerInfo> verify(@RequestParam String hash) {
        return verificationService.verify(hash);
    }
}
