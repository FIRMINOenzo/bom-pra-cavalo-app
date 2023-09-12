package com.bompracavalo.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bompracavalo.api.auth.dtos.CodeDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String authenticateUser(@RequestBody CodeDTO codeDTO) {
        String response = authService.authenticateUser(codeDTO.getCode());

        return response;
    }
}
