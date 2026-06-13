package teknofest.signa.producer.service;

import static teknofest.signa.producer.constants.ErrorConstants.ADMIN_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.enums.Status;
import teknofest.signa.producer.handler.exception.ResourceNotFoundException;
import teknofest.signa.producer.model.dto.backoffice.InfoResponse;
import teknofest.signa.producer.model.entity.Admin;
import teknofest.signa.producer.repository.AdminRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeService {

    private final AdminRepository adminRepository;

    public InfoResponse getInfo(String email) {
        Admin admin = adminRepository.findByEmailAndStatus(email, Status.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException(ADMIN_NOT_FOUND));

        return InfoResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .status(admin.getStatus())
                .role(admin.getRole())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }
}
