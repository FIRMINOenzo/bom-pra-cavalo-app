package com.bompracavalo.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/test")
public class testController {
    @GetMapping
    public String getMethodName() {
        return "Teste";
    }

}
