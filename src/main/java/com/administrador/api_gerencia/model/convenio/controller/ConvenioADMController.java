package com.administrador.api_gerencia.model.convenio.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.convenio.Convenio;
import com.administrador.api_gerencia.model.convenio.service.ConvenioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/area-administrativa/convenios")
public class ConvenioADMController {

    private final ConvenioService service;

    public ConvenioADMController(ConvenioService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Convenio> salvar(@Valid @RequestBody Convenio _convenio) {
        return ResponseEntity.ok(service.salvar(_convenio));
    }

    @PutMapping("{convenioId}")
    public ResponseEntity<Convenio> editar(@PathVariable("convenioId") Long _convenioId, @Valid @RequestBody Convenio _convenio) {
        if(_convenio.getId() == null) {
            throw new ResourceNotFoundException("Id não informado");
        }
        if(_convenio.getId().longValue() != _convenioId) {
            throw new RuntimeException("O id do objeto está diferente do id da url, não pode salvar a alteração");
        }

        Convenio obj = service.buscarPorId(_convenio.getId());
        if(obj == null){
            throw new ResourceNotFoundException("Convênio não encontrado na base de dados.");
        }

        _convenio = service.editar(_convenio);
        return ResponseEntity.ok(_convenio);
    }

    @DeleteMapping("{convenioId}")
    public void deletar(@PathVariable("convenioId") Long _convenioId) {
        service.deletar(_convenioId);
    }

}
