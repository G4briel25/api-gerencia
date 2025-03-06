package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoViewRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioViewDetalhado;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioViewDetalhadoRepository;
import com.administrador.api_gerencia.model.lancamento.convenio.repository.LancamentoConvenioViewRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConvenioDetalhadoService extends GenericService<ConvenioViewDetalhado, Long> {

    private final ConvenioViewDetalhadoRepository repository;

    private final AditivoViewRepository aditivoViewRepository;

    private final LancamentoConvenioViewRepository lancamentoConvenioViewRepository;

    public ConvenioDetalhadoService(ConvenioViewDetalhadoRepository repository, AditivoViewRepository aditivoViewRepository, LancamentoConvenioViewRepository lancamentoConvenioViewRepository) {
        super(repository);
        this.repository = repository;
        this.aditivoViewRepository = aditivoViewRepository;
        this.lancamentoConvenioViewRepository = lancamentoConvenioViewRepository;
    }


    public ConvenioViewDetalhado buscarConvenioDetalhado(Long convenioId) {
        ConvenioViewDetalhado obj = repository.findConvenioDetalhadoById(convenioId);
        if (obj == null) {
            throw new ResourceNotFoundException("Convênio com ID " + convenioId + " não encontrado.");
        }
        obj.setAditivos(aditivoViewRepository.findByConvenioIdIn(Collections.singletonList(convenioId)));
        obj.setLancamento(lancamentoConvenioViewRepository.findByConvenioIdIn(Collections.singletonList(convenioId))
                .stream()
                .filter(lancamento -> lancamento.getAditivoId() == null)
                .toList()
        );
        return obj;
    }

}
