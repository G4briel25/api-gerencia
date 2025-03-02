package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.aditivo.service.AditivoViewService;
import com.administrador.api_gerencia.model.convenio.ConvenioView;
import com.administrador.api_gerencia.model.convenio.service.ConvenioViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos")
public class AditivoController {

    private final AditivoViewService service;
    private final ConvenioViewService convenioViewService;

    public AditivoController(AditivoViewService aditivoViewService, ConvenioViewService convenioViewService) {
        this.service = aditivoViewService;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar")
    public List<AditivoView> listar(@PathVariable("convenioId") Long _convenioId) {
        ConvenioView convenio = convenioViewService.buscarPorId(_convenioId);
        return service.listarAditivoPorConvenioId(_convenioId);
    }

    @GetMapping("{id}")
    public AditivoView buscarPorId(@PathVariable("convenioId") Long _convenioId, @PathVariable("id") Long _id) {
        ConvenioView convenio = convenioViewService.buscarPorId(_convenioId);
        return service.buscarAditivoPorIdEConvenio(_id, _convenioId);
    }

}
