package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.convenio.Convenio;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

@Service
public class ConvenioService extends GenericService<Convenio, Long> {

    public ConvenioService(ConvenioRepository repository) {
        super(repository);
    }

}
