package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.service.AditivoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos")
public class AditivoController {

    private final AditivoService service;

    public AditivoController(AditivoService service) {
        this.service = service;
    }

    @GetMapping("listar")
    public List<Aditivo> listar() {
        return service.listar();
    }

    @GetMapping("{id}")
    public Aditivo buscarPorId(@PathVariable("id") Long _id) {
        return service.buscarPorId(_id);
    }

}
