package teknofest.signa.producer.service;

import static org.springframework.security.core.userdetails.User.withUsername;
import static teknofest.signa.producer.constants.ErrorConstants.ADMIN_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.dto.AuthResponse;
import teknofest.signa.producer.model.dto.LoginRequest;
import teknofest.signa.producer.model.dto.RegisterRequest;
import teknofest.signa.producer.model.entity.Admin;
import teknofest.signa.producer.enums.Status;
import teknofest.signa.producer.handler.exception.ResourceNotFoundException;
import teknofest.signa.producer.repository.AdminRepository;
import teknofest.signa.producer.security.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        log.info("login started for user: {}", email);

        Admin admin = adminRepository.findByEmailAndStatus(email, Status.ACTIVE.name())
                .orElseThrow(() -> new ResourceNotFoundException(ADMIN_NOT_FOUND));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword()));

        return generateAuthResponse(admin);
    }

    public AuthResponse register(RegisterRequest registerRequest, String token) {
        return null;
    }


    private AuthResponse generateAuthResponse(Admin admin) {
        var UserDetails = withUsername(admin.getEmail())
                .password(admin.getPassword())
                .authorities(admin.getRole().name())
                .build();

        return AuthResponse.builder()
                .token(jwtService.generateToken(UserDetails))
                .build();
    }
}
