package com.administrador.api_gerencia.model.lancamento.aditivos.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.lancamento.aditivos.LancamentoAditivoView;
import com.administrador.api_gerencia.model.lancamento.aditivos.repository.LancamentoAditivoViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoAditivoViewService extends GenericService<LancamentoAditivoView, Long> {

    private final LancamentoAditivoViewRepository repository;

    public LancamentoAditivoViewService(LancamentoAditivoViewRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<LancamentoAditivoView> listarLancamentoPorConvenioEAditivoId(Long convenioId, Long aditivoId) {
        return repository.findByConvenioIdAndAditivoId(convenioId, aditivoId);
    }

    public LancamentoAditivoView buscarLancamentoPorId(Long lancamentoId, Long convenioId, Long aditivoId) {
        return repository.findByIdAndConvenioIdAndAditivoId(lancamentoId, convenioId, aditivoId)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado para este convênio e aditivo."));
    }

}
