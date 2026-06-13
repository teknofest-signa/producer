package teknofest.signa.producer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.dto.AuthResponse;
import teknofest.signa.producer.dto.LoginRequest;
import teknofest.signa.producer.dto.RegisterRequest;
import teknofest.signa.producer.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/admin-register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest, @RequestParam String token) {
        return authService.register(registerRequest, token);
    }
}
