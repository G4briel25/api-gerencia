package com.administrador.api_gerencia.controller;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.model.Convenio;
import com.administrador.api_gerencia.model.ConvenioView;
import com.administrador.api_gerencia.model.ConvenioViewDetalhado;
import com.administrador.api_gerencia.service.ConvenioDetalhadoService;
import com.administrador.api_gerencia.service.ConvenioService;
import com.administrador.api_gerencia.service.ConvenioViewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;
    private final ConvenioViewService convenioViewService;
    private final ConvenioDetalhadoService convenioDetalhadoService;

    public ConvenioController(ConvenioService convenioService, ConvenioViewService convenioViewService, ConvenioDetalhadoService convenioDetalhadoService) {
        this.convenioService = convenioService;
        this.convenioViewService = convenioViewService;
        this.convenioDetalhadoService = convenioDetalhadoService;
    }

    @GetMapping("listar")
    public List<ConvenioView> listar() {
        return convenioViewService.listar();
    }

    @GetMapping("{convenioId}")
    public ConvenioViewDetalhado buscarPorId(@PathVariable("convenioId") Long _convenioId) {
        return convenioDetalhadoService.buscarConvenioDetalhado(_convenioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<Convenio> salvar(
            @Valid @RequestBody Convenio _convenio
    ) {
        Convenio obj = convenioService.salvar(_convenio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{convenioId}")
    public ResponseEntity<Convenio> editar(@PathVariable("convenioId") Long _convenioId, @Valid @RequestBody Convenio _convenio) {
        if(_convenio.getId() == null) {
            throw new ResourceNotFoundException("Id não informado");
        }
        if(_convenio.getId().longValue() != _convenioId) {
            throw new RuntimeException("O id do objeto está diferente do id da url, não pode salvar a alteração");
        }

        Convenio obj = convenioService.buscarPorId(_convenio.getId());
        if(obj == null){
            throw new ResourceNotFoundException("Convênio não encontrado na base de dados.");
        }

        _convenio = convenioService.editar(_convenio);
        return ResponseEntity.ok(_convenio);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{convenioId}")
    public void deletar(@PathVariable("convenioId") Long _convenioId) {
        convenioService.deletar(_convenioId);
    }

}
