package com.example.inventorypractice.service;

import com.example.inventorypractice.dto.LoginRequest;
import com.example.inventorypractice.entity.SysUser;
import com.example.inventorypractice.mapper.SysUserMapper;
import com.example.inventorypractice.security.JwtTokenProvider;
import com.example.inventorypractice.vo.LoginVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private SysUserMapper sysUserMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;
    @Test
    void shouldLoginSuccessfully() {
        LoginRequest request = new LoginRequest();
        request.setUsername("backend_intern");
        request.setPassword("Backend123");

        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("backend_intern");
        user.setPassword("encoded-password");
        user.setRole("ADMIN");
        user.setStatus(1);

        when(sysUserMapper.selectOne(any()))
                .thenReturn(user);

        when(passwordEncoder.matches(
                "Backend123",
                "encoded-password"
        )).thenReturn(true);

        when(jwtTokenProvider.generateToken(user))
                .thenReturn("test-token");

        when(jwtTokenProvider.getExpirationSeconds())
                .thenReturn(86400L);

        LoginVO result = authService.login(request);

        assertEquals("test-token", result.getToken());
        assertEquals("Bearer", result.getTokenType());
        assertEquals(86400L, result.getExpiresInSeconds());
    }
}