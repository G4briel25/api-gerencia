package com.administrador.api_gerencia.model.lancamento.controller;

import com.administrador.api_gerencia.model.convenio.service.ConvenioViewService;
import com.administrador.api_gerencia.model.lancamento.LancamentoView;
import com.administrador.api_gerencia.model.lancamento.service.LancamentoViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/lancamentos")
public class LancamentoController {

    private final LancamentoViewService service;
    private final ConvenioViewService convenioViewService;

    public LancamentoController(LancamentoViewService service, ConvenioViewService convenioViewService) {
        this.service = service;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar")
    public List<LancamentoView> listar(@PathVariable("convenioId") Long _convenioId) {
        convenioViewService.buscarPorId(_convenioId);
        return service.listarLancamentoPorConvenioId(_convenioId);
    }

    @GetMapping("{id}")
    public LancamentoView buscarPorId(@PathVariable("convenioId") Long _convenioId, @PathVariable("id") Long _id) {
        convenioViewService.buscarPorId(_convenioId);
        return service.buscarLancamentoPorIdEConvenio(_id, _convenioId);
    }

}
