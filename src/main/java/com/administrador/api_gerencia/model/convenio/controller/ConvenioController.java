package com.administrador.api_gerencia.model.convenio.controller;

import com.administrador.api_gerencia.model.convenio.Convenio;
import com.administrador.api_gerencia.model.convenio.service.ConvenioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios")
public class ConvenioController {

    private final ConvenioService service;

    public ConvenioController(ConvenioService service) {
        this.service = service;
    }

    @GetMapping("listar")
    public List<Convenio> listar() {
        return service.listar();
    }

    @GetMapping("{convenioId}")
    public Convenio buscarPorId(@PathVariable("convenioId") Long _convenioId) {
        return service.buscarPorId(_convenioId);
    }

}
