package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.service.AditivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/aditivos")
public class AditivoADMController {

    private final AditivoService service;

    public AditivoADMController(AditivoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Aditivo> salvar(@PathVariable("convenioId") Long _convenioId, @Valid @RequestBody Aditivo _aditivo) {
        _aditivo.setConvenioId(_convenioId);
        Aditivo obj = service.salvar(_aditivo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }


    @PutMapping("{aditivoId}")
    public ResponseEntity<Aditivo> editar(
            @PathVariable("aditivoId") Long _aditivoId,
            @PathVariable("convenioId") Long _convenioId,
            @Valid @RequestBody Aditivo _aditivo)
    {
        if(_convenioId != _aditivo.getConvenioId()) {
            throw new ResourceNotFoundException("Convênio com ID " + _convenioId + " não encontrado.");
        }
        if(_aditivo.getId() == null) {
            throw new ResolutionException("Id não informado");
        }
        if(_aditivo.getId().longValue() != _aditivoId) {
            throw new RuntimeException("O id do objeto está diferente do id da url, não pode salvar a alteração");
        }

        Aditivo obj = service.buscarPorId(_aditivoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Aditivo não encontrado na base de dados.");
        }

        _aditivo = service.editar(_aditivo);
        return ResponseEntity.ok(_aditivo);
    }

    @DeleteMapping("{_aditivoId}")
    public void deletar(@PathVariable("_aditivoId") Long _aditivoId) {
        service.deletar(_aditivoId);
    }

}
