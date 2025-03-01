package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.service.AditivoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/aditivos")
public class AditivoADMController {

    private final AditivoService service;

    public AditivoADMController(AditivoService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Aditivo salvar(@PathVariable("convenioId") Long convenioId, @Valid @RequestBody Aditivo aditivo) {
        aditivo.setConvenioId(convenioId);
        Aditivo obj = service.salvar(aditivo);
        return obj;
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{aditivoId}")
    public Aditivo editar(@PathVariable("aditivoId") Long _aditivoId, @Valid @RequestBody Aditivo _aditivo) {
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
        return _aditivo;
    }

    @DeleteMapping("{_aditivoId}")
    public void deletar(@PathVariable("_aditivoId") Long _aditivoId) {
        service.deletar(_aditivoId);
    }

}
