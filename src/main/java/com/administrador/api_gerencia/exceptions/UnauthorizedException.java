package com.administrador.api_gerencia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() { super("Usuário não autenticado"); }

    public UnauthorizedException(String message) {
        super(message);
    }
}
