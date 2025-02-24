package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.service.AditivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/aditivos")
public class AditivoADMController {

    private final AditivoService service;

    public AditivoADMController(AditivoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Aditivo> salvar(@PathVariable("convenioId") Long convenioId, @Valid @RequestBody Aditivo aditivo) {
        aditivo.setConvenioId(convenioId);
        Aditivo obj = service.salvar(aditivo);
        return ResponseEntity.ok(obj);
    }


    @PutMapping("{aditivoId}")
    public Aditivo editar(@PathVariable("aditivoId") Long _aditivoId) {
        Aditivo obj = service.buscarPorId(_aditivoId);
        return service.editar(obj);
    }

    @DeleteMapping("{_aditivoId}")
    public void deletar(@PathVariable("_aditivoId") Long _aditivoId) {
        service.deletar(_aditivoId);
    }

}
