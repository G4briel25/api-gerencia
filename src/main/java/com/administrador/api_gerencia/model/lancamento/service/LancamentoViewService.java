package com.administrador.api_gerencia.model.lancamento.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.lancamento.LancamentoView;
import com.administrador.api_gerencia.model.lancamento.repository.LancamentoViewRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoViewService extends GenericService<LancamentoView, Long> {

    private final LancamentoViewRepository repository;

    public LancamentoViewService(LancamentoViewRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
