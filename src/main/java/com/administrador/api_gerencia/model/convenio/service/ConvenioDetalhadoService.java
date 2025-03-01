package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioViewDetalhado;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioViewDetalhadoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConvenioDetalhadoService extends GenericService<ConvenioViewDetalhado, Long> {

    private final ConvenioViewDetalhadoRepository repository;

    private final AditivoRepository aditivoRepository;

    public ConvenioDetalhadoService(ConvenioViewDetalhadoRepository repository, AditivoRepository aditivoRepository) {
        super(repository);
        this.repository = repository;
        this.aditivoRepository = aditivoRepository;
    }


    public ConvenioViewDetalhado buscarConvenioDetalhado(Long convenioId) {
        ConvenioViewDetalhado obj = repository.findConvenioDetalhadoById(convenioId);
        if (obj == null) {
            throw new ResourceNotFoundException("Convênio com ID " + convenioId + " não encontrado.");
        }
        obj.setAditivos(aditivoRepository.findByConvenioIdIn(Collections.singletonList(convenioId)));
        return obj;
    }

}
