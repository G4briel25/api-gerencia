package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.LancamentoConvenioView;
import com.administrador.api_gerencia.repository.LancamentoConvenioViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoConvenioViewService extends GenericService<LancamentoConvenioView, Long> {

    private final LancamentoConvenioViewRepository repository;

    public LancamentoConvenioViewService(LancamentoConvenioViewRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<LancamentoConvenioView> listarLancamentoPorConvenioId(Long convenioId) {
        return repository.findByConvenioIdAndAditivoIdIsNullOrderedByIdDesc(convenioId);
    }

    public LancamentoConvenioView buscarLancamentoPorIdEConvenio(Long lancamentoId, Long convenioId) {
        return repository.findByIdAndConvenioId(lancamentoId, convenioId)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado para este convênio."));
    }

}
