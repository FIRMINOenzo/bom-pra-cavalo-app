package com.bompracavalo.api.auth.dtos;

import org.springframework.lang.NonNull;

public class CodeDTO {
    @NonNull
    private String code;

    public String getCode() {
        return this.code;
    }
}
