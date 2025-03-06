package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.LancamentoConvenio;
import com.administrador.api_gerencia.repository.LancamentoConvenioRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoConvenioService extends GenericService<LancamentoConvenio, Long> {

    public LancamentoConvenioService(LancamentoConvenioRepository repository) {
        super(repository);
    }

}
