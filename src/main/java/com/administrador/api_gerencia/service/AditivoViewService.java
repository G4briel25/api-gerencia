package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.AditivoView;
import com.administrador.api_gerencia.repository.AditivoViewRepository;
import com.administrador.api_gerencia.repository.LancamentoAditivoViewRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AditivoViewService extends GenericService<AditivoView, Long> {

    private final AditivoViewRepository repository;
    private final LancamentoAditivoViewRepository lancamentoAditivoViewRepository;

    public AditivoViewService(AditivoViewRepository repository, LancamentoAditivoViewRepository lancamentoAditivoViewRepository) {
        super(repository);
        this.repository = repository;
        this.lancamentoAditivoViewRepository = lancamentoAditivoViewRepository;
    }

    public List<AditivoView> listarAditivoPorConvenioId(Long convenioId) {
        List<AditivoView> aditivos = repository.findByConvenioId(convenioId);

        // Para cada aditivo, buscar e adicionar os lançamentos
        aditivos.forEach(aditivo ->
                aditivo.setLancamento(lancamentoAditivoViewRepository.findByAditivoId(aditivo.getId()))
        );

        return aditivos;
    }

    public AditivoView buscarAditivoPorIdEConvenio(Long aditivoId, Long convenioId) {
        AditivoView obj = repository.findByIdAndConvenioId(aditivoId, convenioId)
                .orElseThrow(() -> new ResourceNotFoundException("Aditivo não encontrado para este convênio."));;
                obj.setLancamento(lancamentoAditivoViewRepository.findByAditivoIdIn(Collections.singletonList(aditivoId)));
        return obj;
    }

}
