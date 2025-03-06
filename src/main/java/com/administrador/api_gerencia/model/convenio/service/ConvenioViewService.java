package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.convenio.ConvenioView;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioViewRepository;
import org.springframework.stereotype.Service;

@Service
public class ConvenioViewService extends GenericService<ConvenioView, Long> {

    public ConvenioViewService(ConvenioViewRepository repository) {
        super(repository);
    }

}
