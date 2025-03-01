package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.aditivo.service.AditivoService;
import com.administrador.api_gerencia.model.aditivo.service.AditivoViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos")
public class AditivoController {

    private final AditivoService service;
    private final AditivoViewService aditivoViewService;

    public AditivoController(AditivoService service, AditivoViewService aditivoViewService) {
        this.service = service;
        this.aditivoViewService = aditivoViewService;
    }

    @GetMapping("listar")
    public List<Aditivo> listar() {
        return service.listar();
    }

    @GetMapping("{id}")
    public AditivoView buscarPorId(@PathVariable("id") Long _id) {
        return aditivoViewService.buscarPorId(_id);
    }

}
