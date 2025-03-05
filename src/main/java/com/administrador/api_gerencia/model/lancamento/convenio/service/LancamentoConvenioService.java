package com.administrador.api_gerencia.model.lancamento.convenio.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenio;
import com.administrador.api_gerencia.model.lancamento.convenio.repository.LancamentoConvenioRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoConvenioService extends GenericService<LancamentoConvenio, Long> {

    public LancamentoConvenioService(LancamentoConvenioRepository repository) {
        super(repository);
    }

}
