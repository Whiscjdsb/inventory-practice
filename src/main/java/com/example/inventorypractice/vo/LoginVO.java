package com.example.inventorypractice.vo;

import lombok.Data;

@Data
public class LoginVO {
    private String token;
    private String tokenType;
    private long expiresInSeconds;
}
