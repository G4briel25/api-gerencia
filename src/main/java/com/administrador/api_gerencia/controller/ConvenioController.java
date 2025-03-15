package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.model.ConvenioView;
import com.administrador.api_gerencia.model.ConvenioViewDetalhado;
import com.administrador.api_gerencia.service.ConvenioDetalhadoService;
import com.administrador.api_gerencia.service.ConvenioViewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios")
public class ConvenioController {

    private final ConvenioViewService convenioViewService;
    private final ConvenioDetalhadoService convenioDetalhadoService;

    public ConvenioController(ConvenioViewService convenioViewService, ConvenioDetalhadoService convenioDetalhadoService) {
        this.convenioViewService = convenioViewService;
        this.convenioDetalhadoService = convenioDetalhadoService;
    }

    @GetMapping("listar")
    public List<ConvenioView> listar() {
        return convenioViewService.listar();
    }

    @GetMapping("{convenioId}")
    public ConvenioViewDetalhado buscarPorId(@PathVariable("convenioId") Long _convenioId) {
        return convenioDetalhadoService.buscarConvenioDetalhado(_convenioId);
    }

}
