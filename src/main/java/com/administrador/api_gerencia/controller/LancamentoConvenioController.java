package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.LancamentoConvenio;
import com.administrador.api_gerencia.service.ConvenioViewService;
import com.administrador.api_gerencia.model.LancamentoConvenioView;
import com.administrador.api_gerencia.service.LancamentoConvenioService;
import com.administrador.api_gerencia.service.LancamentoConvenioViewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/lancamentos")
public class LancamentoConvenioController {

    private final LancamentoConvenioViewService service;
    private final LancamentoConvenioService lancamentoConvenioService;
    private final ConvenioViewService convenioViewService;

    public LancamentoConvenioController(LancamentoConvenioViewService service, LancamentoConvenioService lancamentoConvenioService, ConvenioViewService convenioViewService) {
        this.service = service;
        this.lancamentoConvenioService = lancamentoConvenioService;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar-lancamentos")
    public List<LancamentoConvenioView> listar(@PathVariable("convenioId") Long _convenioId) {
        convenioViewService.buscarPorId(_convenioId);
        return service.listarLancamentoPorConvenioId(_convenioId);
    }

    @GetMapping("{id}")
    public LancamentoConvenioView buscarPorId(@PathVariable("convenioId") Long _convenioId, @PathVariable("id") Long _id) {
        convenioViewService.buscarPorId(_convenioId);
        return service.buscarLancamentoPorIdEConvenio(_id, _convenioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<LancamentoConvenio> salvar(@PathVariable("convenioId") Long _convenioId, @Valid @RequestBody LancamentoConvenio _lancamentoConvenio) {
        _lancamentoConvenio.setConvenioId(_convenioId);
        LancamentoConvenio obj = lancamentoConvenioService.salvar(_lancamentoConvenio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
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

        LancamentoConvenio obj = lancamentoConvenioService.buscarPorId(_lancamentoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Lançamento não encontrado na base de dados.");
        }

        _lancamentoConvenio = lancamentoConvenioService.editar(_lancamentoConvenio);
        return ResponseEntity.ok(_lancamentoConvenio);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{lancamentoId}")
    public void deletar(@PathVariable("lancamentoId") Long _lancamentoId) {
        lancamentoConvenioService.deletar(_lancamentoId);
    }

}
