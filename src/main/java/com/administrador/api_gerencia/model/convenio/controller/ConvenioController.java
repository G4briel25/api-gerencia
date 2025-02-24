package com.administrador.api_gerencia.model.convenio.controller;

import com.administrador.api_gerencia.model.convenio.Convenio;
import com.administrador.api_gerencia.model.convenio.ConvenioDetalhado;
import com.administrador.api_gerencia.model.convenio.service.ConvenioDetalhadoService;
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
    private final ConvenioDetalhadoService convenioDetalhadoService;

    public ConvenioController(ConvenioService service, ConvenioDetalhadoService convenioDetalhadoService) {
        this.service = service;
        this.convenioDetalhadoService = convenioDetalhadoService;
    }

    @GetMapping("listar")
    public List<Convenio> listar() {
        return service.listar();
    }

    @GetMapping("{convenioId}")
    public ConvenioDetalhado buscarPorId(@PathVariable("convenioId") Long _convenioId) {

        return convenioDetalhadoService.buscarConvenioDetalhado(_convenioId);
    }

}
