package com.administrador.api_gerencia.security.services;

import com.administrador.api_gerencia.security.dto.AcessDTO;
import com.administrador.api_gerencia.security.dto.AuthenticationDTO;
import com.administrador.api_gerencia.security.jwt.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

            System.out.println("=====================================");
            System.out.println("Usuário " + authDto.getUsername() + " conectado ao sistema.");
            System.out.println("=====================================");

            return accessDto;

        }catch(BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
