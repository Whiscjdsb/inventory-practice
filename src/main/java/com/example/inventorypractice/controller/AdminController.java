package com.example.inventorypractice.controller;

import com.example.inventorypractice.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.success("管理员接口访问成功");
    }
}