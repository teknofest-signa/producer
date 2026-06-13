package teknofest.signa.producer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.CreateAdminRequest;
import teknofest.signa.producer.service.SuperAdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/super-admins")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @PostMapping("/create-admin")
    public void createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        superAdminService.createAdmin(createAdminRequest);
    }
}
