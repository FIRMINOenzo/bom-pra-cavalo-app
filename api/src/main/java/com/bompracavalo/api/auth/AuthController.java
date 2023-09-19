package com.bompracavalo.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bompracavalo.api.auth.dtos.AuthenticationResponse;
import com.bompracavalo.api.auth.dtos.CodeDTO;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody CodeDTO dto) {
        var response = authService.authenticateUser(dto.getCode());

        if (response.getJwt() != null) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
