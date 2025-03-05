package com.administrador.api_gerencia.model.lancamento.controller;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.lancamento.Lancamento;
import com.administrador.api_gerencia.model.lancamento.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/lancamentos")
public class LancamentoADMController {

    private final LancamentoService service;

    public LancamentoADMController(LancamentoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Lancamento> salvar(@PathVariable("convenioId") Long convenioId, @Valid @RequestBody Lancamento lancamento) {
        lancamento.setConvenioId(convenioId);
        Lancamento obj = service.salvar(lancamento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

}
