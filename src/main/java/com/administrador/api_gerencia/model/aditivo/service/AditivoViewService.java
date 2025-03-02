package com.administrador.api_gerencia.model.aditivo.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AditivoViewService extends GenericService<AditivoView, Long> {

    private final AditivoViewRepository repository;

    public AditivoViewService(AditivoViewRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<AditivoView> listarAditivoPorConvenioId(Long convenioId) {
        return repository.findByConvenioId(convenioId);
    }

    public AditivoView buscarAditivoPorIdEConvenio(Long aditivoId, Long convenioId) {
        return repository.findByIdAndConvenioId(aditivoId, convenioId)
                .orElseThrow(() -> new ResourceNotFoundException("Aditivo não encontrado para este convênio."));
    }

}
