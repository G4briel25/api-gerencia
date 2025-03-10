package com.administrador.api_gerencia.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcessDTO {

    private String token;

    // implementar retornar o usuario e liberacoes (authorities)

}
