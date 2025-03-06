package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.service.ConvenioViewService;
import com.administrador.api_gerencia.model.LancamentoConvenioView;
import com.administrador.api_gerencia.service.LancamentoConvenioViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/lancamentos")
public class LancamentoConvenioController {

    private final LancamentoConvenioViewService service;
    private final ConvenioViewService convenioViewService;

    public LancamentoConvenioController(LancamentoConvenioViewService service, ConvenioViewService convenioViewService) {
        this.service = service;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar")
    public List<LancamentoConvenioView> listar(@PathVariable("convenioId") Long _convenioId) {
        convenioViewService.buscarPorId(_convenioId);
        return service.listarLancamentoPorConvenioId(_convenioId);
    }

    @GetMapping("{id}")
    public LancamentoConvenioView buscarPorId(@PathVariable("convenioId") Long _convenioId, @PathVariable("id") Long _id) {
        convenioViewService.buscarPorId(_convenioId);
        return service.buscarLancamentoPorIdEConvenio(_id, _convenioId);
    }

}
