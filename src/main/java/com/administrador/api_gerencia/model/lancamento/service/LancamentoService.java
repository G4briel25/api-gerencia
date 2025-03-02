package com.administrador.api_gerencia.model.lancamento.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.lancamento.Lancamento;
import com.administrador.api_gerencia.model.lancamento.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService extends GenericService<Lancamento, Long> {

    public LancamentoService(LancamentoRepository repository) {
        super(repository);
    }

}
