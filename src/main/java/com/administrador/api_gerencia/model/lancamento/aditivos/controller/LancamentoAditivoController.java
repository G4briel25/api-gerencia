package com.administrador.api_gerencia.model.lancamento.aditivos.controller;

import com.administrador.api_gerencia.model.convenio.service.ConvenioViewService;
import com.administrador.api_gerencia.model.lancamento.aditivos.LancamentoAditivoView;
import com.administrador.api_gerencia.model.lancamento.aditivos.service.LancamentoAditivoViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos/{aditivoId}/lancamentos")
public class LancamentoAditivoController {

    private final LancamentoAditivoViewService service;
    private final ConvenioViewService convenioViewService;

    public LancamentoAditivoController(LancamentoAditivoViewService service, ConvenioViewService convenioViewService) {
        this.service = service;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar")
    public List<LancamentoAditivoView> listar(
            @PathVariable("convenioId") Long _convenioId,
            @PathVariable("aditivoId") Long _aditivoId
    ) {
        convenioViewService.buscarPorId(_convenioId);
        return service.listarLancamentoPorConvenioEAditivoId(_convenioId, _aditivoId);
    }

    @GetMapping("{id}")
    public LancamentoAditivoView buscarPorId(
            @PathVariable("convenioId") Long _convenioId,
            @PathVariable("aditivoId") Long _aditivoId,
            @PathVariable("id") Long _id
    ) {
        convenioViewService.buscarPorId(_convenioId);
        return service.buscarLancamentoPorId(_id, _convenioId, _aditivoId);
    }

}
