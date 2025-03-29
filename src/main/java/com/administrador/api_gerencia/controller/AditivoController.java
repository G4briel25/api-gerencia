package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.Aditivo;
import com.administrador.api_gerencia.model.AditivoView;
import com.administrador.api_gerencia.service.AditivoService;
import com.administrador.api_gerencia.service.AditivoViewService;
import com.administrador.api_gerencia.service.ConvenioViewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.module.ResolutionException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/convenios/{convenioId}/aditivos")
public class AditivoController {

    private final AditivoService aditivoService;
    private final AditivoViewService service;
    private final ConvenioViewService convenioViewService;

    public AditivoController(AditivoService aditivoService, AditivoViewService aditivoViewService, ConvenioViewService convenioViewService) {
        this.aditivoService = aditivoService;
        this.service = aditivoViewService;
        this.convenioViewService = convenioViewService;
    }

    @GetMapping("listar-aditivos")
    public List<AditivoView> listar(@PathVariable("convenioId") Long _convenioId) {
        convenioViewService.buscarPorId(_convenioId);
        return service.listarAditivoPorConvenioId(_convenioId);
    }

    @GetMapping("{id}")
    public AditivoView buscarPorId(@PathVariable("convenioId") Long _convenioId, @PathVariable("id") Long _id) {
        convenioViewService.buscarPorId(_convenioId);
        return service.buscarAditivoPorIdEConvenio(_id, _convenioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<Aditivo> salvar(
            @PathVariable("convenioId") Long _convenioId,
            @Valid @RequestBody Aditivo _aditivo
    ) {
        _aditivo.setConvenioId(_convenioId);
        Aditivo obj = aditivoService.salvar(_aditivo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }


    @PreAuthorize("hasRole('ADMIN')")
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

        Aditivo obj = aditivoService.buscarPorId(_aditivoId);
        if (obj == null) {
            throw new ResourceNotFoundException("Aditivo não encontrado na base de dados.");
        }

        _aditivo = aditivoService.editar(_aditivo);
        return ResponseEntity.ok(_aditivo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{_aditivoId}")
    public void deletar(@PathVariable("_aditivoId") Long _aditivoId) {
        aditivoService.deletar(_aditivoId);
    }

}
