package com.administrador.api_gerencia.model.aditivo.controller;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.service.AditivoService;
import com.administrador.api_gerencia.model.convenio.Convenio;
import com.administrador.api_gerencia.model.convenio.service.ConvenioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/area-administrativa/convenios/{convenioId}/aditivos")
public class AditivoADMController {

    private final AditivoService service;
    private final ConvenioService convenioService;

    public AditivoADMController(AditivoService service, ConvenioService convenioService) {
        this.service = service;
        this.convenioService = convenioService;
    }

    @PostMapping
    public ResponseEntity<Aditivo> salvar(@PathVariable("convenioId") Long convenioId, @Valid @RequestBody Aditivo aditivo) {
        Convenio convenio = convenioService.buscarPorId(convenioId);
        if (convenio == null) {
            return ResponseEntity.notFound().build();
        }
        aditivo.setId(convenio);
        return ResponseEntity.ok(aditivo);
    }


    @PutMapping("{_aditivoId}")
    public Aditivo editar(@PathVariable("_aditivoId") Long _aditivoId) {
        Aditivo obj = service.buscarPorId(_aditivoId);
        return service.editar(obj);
    }

    @DeleteMapping("{_aditivoId}")
    public void deletar(@PathVariable("_aditivoId") Long _aditivoId) {
        service.deletar(_aditivoId);
    }

}
