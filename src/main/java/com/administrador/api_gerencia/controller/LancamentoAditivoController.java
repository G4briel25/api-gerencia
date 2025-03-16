package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.LancamentoAditivo;
import com.administrador.api_gerencia.service.ConvenioViewService;
import com.administrador.api_gerencia.model.LancamentoAditivoView;
import com.administrador.api_gerencia.service.LancamentoAditivoService;
import com.administrador.api_gerencia.service.LancamentoAditivoViewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos/{aditivoId}/lancamentos")
public class LancamentoAditivoController {

    private final LancamentoAditivoService lancamentoAditivoService;
    private final LancamentoAditivoViewService service;
    private final ConvenioViewService convenioViewService;

    public LancamentoAditivoController(LancamentoAditivoService lancamentoAditivoService, LancamentoAditivoViewService service, ConvenioViewService convenioViewService) {
        this.lancamentoAditivoService = lancamentoAditivoService;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<LancamentoAditivo> salvar(
            @PathVariable("convenioId") Long _convenioId,
            @PathVariable("aditivoId") Long _aditivoId,
            @Valid @RequestBody LancamentoAditivo _lancamentoAditivo
    ) {
        _lancamentoAditivo.setConvenioId(_convenioId);
        _lancamentoAditivo.setAditivoId(_aditivoId);
        LancamentoAditivo obj = lancamentoAditivoService.salvar(_lancamentoAditivo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{lancamentoId}")
    public ResponseEntity<LancamentoAditivo> editar(
            @PathVariable("lancamentoId") Long _lancamentoId,
            @PathVariable("convenioId") Long _convenioId,
            @PathVariable("aditivoId") Long _aditivoId,
            @Valid @RequestBody LancamentoAditivo _lancamentoAditivo)
    {
        if(_convenioId != _lancamentoAditivo.getConvenioId()) {
            throw new ResourceNotFoundException("Convênio com ID " + _convenioId + " não encontrado.");
        }
        if(_aditivoId != _lancamentoAditivo.getAditivoId()) {
            throw new ResourceNotFoundException("Aditivo com ID " + _aditivoId + " não encontrado.");
        }
        if(_lancamentoAditivo.getId() == null) {
            throw new ResolutionException("Id não informado");
        }
        if(_lancamentoAditivo.getId().longValue() != _lancamentoId) {
            throw new RuntimeException("O id do objeto está diferente do id da url, não pode salvar a alteração");
        }

        LancamentoAditivo obj = lancamentoAditivoService.buscarPorId(_lancamentoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Lançamento não encontrado na base de dados.");
        }

        _lancamentoAditivo = lancamentoAditivoService.editar(_lancamentoAditivo);
        return ResponseEntity.ok(_lancamentoAditivo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{_lancamentoId}")
    public void deletar(@PathVariable("_lancamentoId") Long _lancamentoId) {
        lancamentoAditivoService.deletar(_lancamentoId);
    }

}
