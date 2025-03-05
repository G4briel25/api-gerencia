package com.administrador.api_gerencia.model.lancamento.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.lancamento.Lancamento;
import com.administrador.api_gerencia.model.lancamento.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/lancamentos")
public class LancamentoADMController {

    private final LancamentoService service;

    public LancamentoADMController(LancamentoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Lancamento> salvar(@PathVariable("convenioId") Long _convenioId, @Valid @RequestBody Lancamento _lancamento) {
        _lancamento.setConvenioId(_convenioId);
        Lancamento obj = service.salvar(_lancamento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @PutMapping("{lancamentoId}")
    public ResponseEntity<Lancamento> editar(
            @PathVariable("lancamentoId") Long _lancamentoId,
            @PathVariable("convenioId") Long _convenioId,
            @Valid @RequestBody Lancamento _lancamento)
    {
        if(_convenioId != _lancamento.getConvenioId()) {
            throw new ResourceNotFoundException("Convênio com ID " + _convenioId + " não encontrado.");
        }
        if(_lancamento.getId() == null) {
            throw new ResolutionException("Id não informado");
        }
        if(_lancamento.getId().longValue() != _lancamentoId) {
            throw new RuntimeException("O id do objeto está diferente do id da url, não pode salvar a alteração");
        }

        Lancamento obj = service.buscarPorId(_lancamentoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Lançamento não encontrado na base de dados.");
        }

        _lancamento = service.editar(_lancamento);
        return ResponseEntity.ok(_lancamento);
    }

    @DeleteMapping("{_lancamentoId}")
    public void deletar(@PathVariable("_lancamentoId") Long _lancamentoId) {
        service.deletar(_lancamentoId);
    }

}
