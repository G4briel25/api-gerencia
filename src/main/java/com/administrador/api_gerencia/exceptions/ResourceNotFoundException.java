package com.administrador.api_gerencia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("ID não encontrado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
