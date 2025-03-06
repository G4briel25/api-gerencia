package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.Convenio;
import com.administrador.api_gerencia.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

@Service
public class ConvenioService extends GenericService<Convenio, Long> {

    public ConvenioService(ConvenioRepository repository) {
        super(repository);
    }

}
