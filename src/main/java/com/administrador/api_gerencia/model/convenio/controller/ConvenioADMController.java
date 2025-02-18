package com.administrador.api_gerencia.model.convenio.controller;

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
        if(_convenio == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.salvar(_convenio));
    }

    @PutMapping("{convenioId}")
    public Convenio editar(@PathVariable("convenioId") Long _convenioId) {
        Convenio obj = service.buscarPorId(_convenioId);
        return service.editar(obj);
    }

    @DeleteMapping("{convenioId}")
    public void deletar(@PathVariable("convenioId") Long _convenioId) {
        service.deletar(_convenioId);
    }

}
