package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.ConvenioView;
import com.administrador.api_gerencia.repository.ConvenioViewRepository;
import org.springframework.stereotype.Service;

@Service
public class ConvenioViewService extends GenericService<ConvenioView, Long> {

    public ConvenioViewService(ConvenioViewRepository repository) {
        super(repository);
    }

}
