package teknofest.signa.producer.controller;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.backoffice.InfoResponse;
import teknofest.signa.producer.service.BackofficeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/backoffice")
public class BackofficeController {

    private final BackofficeService backofficeService;

    @GetMapping
    public InfoResponse getInfo(Principal principal) {
        return backofficeService.getInfo(principal.getName());
    }
}
