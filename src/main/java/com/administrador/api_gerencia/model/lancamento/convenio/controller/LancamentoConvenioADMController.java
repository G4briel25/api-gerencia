package com.administrador.api_gerencia.model.lancamento.convenio.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenio;
import com.administrador.api_gerencia.model.lancamento.convenio.service.LancamentoConvenioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/lancamentos")
public class LancamentoConvenioADMController {

    private final LancamentoConvenioService service;

    public LancamentoConvenioADMController(LancamentoConvenioService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<LancamentoConvenio> salvar(@PathVariable("convenioId") Long _convenioId, @Valid @RequestBody LancamentoConvenio _lancamentoConvenio) {
        _lancamentoConvenio.setConvenioId(_convenioId);
        LancamentoConvenio obj = service.salvar(_lancamentoConvenio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @PutMapping("{lancamentoId}")
    public ResponseEntity<LancamentoConvenio> editar(
            @PathVariable("lancamentoId") Long _lancamentoId,
            @PathVariable("convenioId") Long _convenioId,
            @Valid @RequestBody LancamentoConvenio _lancamentoConvenio)
    {
        if(_convenioId != _lancamentoConvenio.getConvenioId()) {
            throw new ResourceNotFoundException("Convênio com ID " + _convenioId + " não encontrado.");
        }
        if(_lancamentoConvenio.getId() == null) {
            throw new ResolutionException("Id não informado");
        }
        if(_lancamentoConvenio.getId().longValue() != _lancamentoId) {
            throw new RuntimeException("O id do objeto está diferente do id da url, não pode salvar a alteração");
        }

        LancamentoConvenio obj = service.buscarPorId(_lancamentoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Lançamento não encontrado na base de dados.");
        }

        _lancamentoConvenio = service.editar(_lancamentoConvenio);
        return ResponseEntity.ok(_lancamentoConvenio);
    }

    @DeleteMapping("{_lancamentoId}")
    public void deletar(@PathVariable("_lancamentoId") Long _lancamentoId) {
        service.deletar(_lancamentoId);
    }

}
