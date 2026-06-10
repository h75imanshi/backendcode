package com.eoffice.controller;

import com.eoffice.dto.AuthDTO.*;
import com.eoffice.model.User;
import com.eoffice.repository.UserRepository;
import com.eoffice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody SignupRequest request) {

        ApiResponse response =
                authService.signup(request);

        if(response.isSuccess()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest()
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest) {

        System.out.println("LOGIN API HIT");
        System.out.println("Username = " + request.getUsername());

        ApiResponse response =
                authService.login(request, httpRequest);

        System.out.println("Response = " + response);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("eOffice API is running!");
    }
}