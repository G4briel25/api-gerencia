package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.model.Aditivo;
import com.administrador.api_gerencia.model.AditivoView;
import com.administrador.api_gerencia.service.AditivoService;
import com.administrador.api_gerencia.service.AditivoViewService;
import com.administrador.api_gerencia.service.ConvenioViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos")
public class AditivoController {

    private final AditivoService aditivoService;
    private final AditivoViewService service;
    private final ConvenioViewService convenioViewService;

    public AditivoController(AditivoService aditivoService, AditivoViewService aditivoViewService, ConvenioViewService convenioViewService) {
        this.aditivoService = aditivoService;
        this.service = aditivoViewService;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar")
    public List<Aditivo> listar() {
        return aditivoService.listar();
    }

    @GetMapping("{id}")
    public AditivoView buscarPorId(@PathVariable("convenioId") Long _convenioId, @PathVariable("id") Long _id) {
        convenioViewService.buscarPorId(_convenioId);
        return service.buscarAditivoPorIdEConvenio(_id, _convenioId);
    }

}
