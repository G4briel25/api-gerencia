package com.administrador.api_gerencia.model.aditivo.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoViewRepository;
import com.administrador.api_gerencia.model.lancamento.aditivos.repository.LancamentoAditivoRepository;
import com.administrador.api_gerencia.model.lancamento.aditivos.repository.LancamentoAditivoViewRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AditivoViewService extends GenericService<AditivoView, Long> {

    private final AditivoViewRepository repository;
    private final LancamentoAditivoRepository lancamentoAditivoRepository;

    public AditivoViewService(AditivoViewRepository repository, LancamentoAditivoRepository lancamentoAditivoRepository) {
        super(repository);
        this.repository = repository;
        this.lancamentoAditivoRepository = lancamentoAditivoRepository;
    }

    public List<AditivoView> listarAditivoPorConvenioId(Long convenioId) {
        return repository.findByConvenioId(convenioId);
    }

    public AditivoView buscarAditivoPorIdEConvenio(Long aditivoId, Long convenioId) {
        AditivoView obj = repository.findByIdAndConvenioId(aditivoId, convenioId)
                .orElseThrow(() -> new ResourceNotFoundException("Aditivo não encontrado para este convênio."));;
                obj.setLancamento(lancamentoAditivoRepository.findByAditivoIdIn(Collections.singletonList(aditivoId)));
        return obj;
    }

}
