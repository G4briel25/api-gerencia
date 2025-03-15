package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.model.Usuario;
import com.administrador.api_gerencia.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Usuario> listarTodos() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario usuario) {
        Usuario obj = service.salvar(usuario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario obj = service.editar(usuario);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
