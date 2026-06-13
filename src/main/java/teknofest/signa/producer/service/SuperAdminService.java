package teknofest.signa.producer.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.dto.CreateAdminRequest;
import teknofest.signa.producer.model.entity.Admin;
import teknofest.signa.producer.enums.Role;
import teknofest.signa.producer.enums.Status;
import teknofest.signa.producer.model.event.CreateAdminEvent;
import teknofest.signa.producer.repository.AdminRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SuperAdminService {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void createAdmin(CreateAdminRequest createAdminRequest) {
        Admin admin = Admin.builder()
                .role(Role.ADMIN)
                .email(createAdminRequest.getEmail())
                .status(Status.PENDING)
                .username(USERNAME)
                .password(passwordEncoder.encode(PASSWORD))
                .token(UUID.randomUUID().toString())
                .build();
        adminRepository.save(admin);

        applicationEventPublisher.publishEvent(new CreateAdminEvent(
               admin.getEmail(),
               admin.getToken()
        ));
    }
}
