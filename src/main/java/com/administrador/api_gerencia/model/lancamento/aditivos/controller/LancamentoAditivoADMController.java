package com.administrador.api_gerencia.model.lancamento.aditivos.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.lancamento.aditivos.LancamentoAditivo;
import com.administrador.api_gerencia.model.lancamento.aditivos.service.LancamentoAditivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/aditivos/{aditivoId}/lancamentos")
public class LancamentoAditivoADMController {

    private final LancamentoAditivoService service;

    public LancamentoAditivoADMController(LancamentoAditivoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<LancamentoAditivo> salvar(
            @PathVariable("convenioId") Long _convenioId,
            @PathVariable("aditivoId") Long _aditivoId,
            @Valid @RequestBody LancamentoAditivo _lancamentoAditivo
    ) {
        _lancamentoAditivo.setConvenioId(_convenioId);
        _lancamentoAditivo.setAditivoId(_aditivoId);
        LancamentoAditivo obj = service.salvar(_lancamentoAditivo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

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

        LancamentoAditivo obj = service.buscarPorId(_lancamentoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Lançamento não encontrado na base de dados.");
        }

        _lancamentoAditivo = service.editar(_lancamentoAditivo);
        return ResponseEntity.ok(_lancamentoAditivo);
    }

    @DeleteMapping("{_lancamentoId}")
    public void deletar(@PathVariable("_lancamentoId") Long _lancamentoId) {
        service.deletar(_lancamentoId);
    }

}
