package com.example.inventorypractice.controller;

import com.example.inventorypractice.common.ApiResponse;
import com.example.inventorypractice.dto.LoginRequest;
import com.example.inventorypractice.dto.RegisterRequest;
import com.example.inventorypractice.service.AuthService;
import com.example.inventorypractice.vo.LoginVO;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ApiResponse<Void> register(@Valid@RequestBody RegisterRequest request) {
        authService.register(request);
        return ApiResponse.success(null);
    }
    @PostMapping("/login")
    public ApiResponse<LoginVO> login(@Valid@RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }
    @GetMapping("/me")
    public ApiResponse<String> getCurrentUser(
            Authentication authentication) {
        return ApiResponse.success(authentication.getName());
    }
}
