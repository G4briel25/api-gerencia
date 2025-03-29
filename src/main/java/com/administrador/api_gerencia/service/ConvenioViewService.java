package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.ConvenioView;
import com.administrador.api_gerencia.repository.ConvenioViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioViewService extends GenericService<ConvenioView, Long> {

    private final ConvenioViewRepository convenioViewRepository;

    public ConvenioViewService(ConvenioViewRepository repository) {
        super(repository);
        this.convenioViewRepository = repository;
    }

    public List<ConvenioView> listar() {
        return convenioViewRepository.findAllOrderedByIdDesc();
    }

}
