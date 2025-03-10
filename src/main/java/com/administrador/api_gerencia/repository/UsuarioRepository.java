package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends GenericRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}
