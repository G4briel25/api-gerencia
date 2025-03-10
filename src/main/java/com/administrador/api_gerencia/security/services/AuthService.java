package com.administrador.api_gerencia.security.services;

import com.administrador.api_gerencia.security.dto.AcessDTO;
import com.administrador.api_gerencia.security.dto.AuthenticationDTO;
import com.administrador.api_gerencia.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticatioManager;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticatioManager, JwtUtils jwtUtils) {
        this.authenticatioManager = authenticatioManager;
        this.jwtUtils = jwtUtils;
    }

    public AcessDTO login(AuthenticationDTO authDto) {

        try {
            //Cria mecanismo de credencial para o spring
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

            //Prepara mecanismo para autenticacao
            Authentication authentication = authenticatioManager.authenticate(userAuth);

            //Busca usuario logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO accessDto = new AcessDTO(token);

            return accessDto;

        }catch(BadCredentialsException e) {
            //TODO LOGIN OU SENHA INVALIDO
        }
        return new AcessDTO("Acesso negado");
    }

}
