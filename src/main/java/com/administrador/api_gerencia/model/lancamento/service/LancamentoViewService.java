package com.administrador.api_gerencia.model.lancamento.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.lancamento.LancamentoView;
import com.administrador.api_gerencia.model.lancamento.repository.LancamentoViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoViewService extends GenericService<LancamentoView, Long> {

    private final LancamentoViewRepository repository;

    public LancamentoViewService(LancamentoViewRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<LancamentoView> listarLancamentoPorConvenioId(Long convenioId) {
        return repository.findByConvenioId(convenioId);
    }

    public LancamentoView buscarLancamentoPorIdEConvenio(Long lancamentoId, Long convenioId) {
        return repository.findByIdAndConvenioId(lancamentoId, convenioId)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado para este convênio."));
    }

}
