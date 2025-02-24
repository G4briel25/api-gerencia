package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioDetalhado;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioDetalhadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConvenioDetalhadoService extends GenericService<ConvenioDetalhado, Long> {

    @Autowired
    private ConvenioDetalhadoRepository repository;

    @Autowired
    private AditivoRepository aditivoRepository;

    public ConvenioDetalhadoService(ConvenioDetalhadoRepository repository) {
        super(repository);
    }

    public ConvenioDetalhado buscarConvenioDetalhado(Long convenioId) {
        ConvenioDetalhado obj = repository.findConvenioDetalhadoById(convenioId);
        obj.setAditivos(aditivoRepository.findByConvenioIdIn(Collections.singletonList(convenioId)));
        return obj;
    }

}
