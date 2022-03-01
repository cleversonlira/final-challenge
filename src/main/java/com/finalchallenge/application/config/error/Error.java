package com.finalchallenge.application.config.error;

import lombok.Getter;

@Getter
public class Error {

    private String erro;
    private String field;

    public Error(String erro, String field) {
        this.erro = erro;
        this.field = field;
    }

}
