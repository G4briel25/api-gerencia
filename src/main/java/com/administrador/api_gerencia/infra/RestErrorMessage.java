package com.administrador.api_gerencia.infra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class RestErrorMessage {
    private HttpStatus status;
    private String message;

    public RestErrorMessage(HttpStatus _status, String _message) {
        this.status = _status;
        this.message = _message;
    }
}
