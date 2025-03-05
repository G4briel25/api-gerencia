package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioViewDetalhado;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioViewDetalhadoRepository;
import com.administrador.api_gerencia.model.lancamento.convenio.repository.LancamentoConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConvenioDetalhadoService extends GenericService<ConvenioViewDetalhado, Long> {

    private final ConvenioViewDetalhadoRepository repository;

    private final AditivoRepository aditivoRepository;

    private final LancamentoConvenioRepository lancamentoConvenioRepository;

    public ConvenioDetalhadoService(ConvenioViewDetalhadoRepository repository, AditivoRepository aditivoRepository, LancamentoConvenioRepository lancamentoConvenioRepository) {
        super(repository);
        this.repository = repository;
        this.aditivoRepository = aditivoRepository;
        this.lancamentoConvenioRepository = lancamentoConvenioRepository;
    }


    public ConvenioViewDetalhado buscarConvenioDetalhado(Long convenioId) {
        ConvenioViewDetalhado obj = repository.findConvenioDetalhadoById(convenioId);
        if (obj == null) {
            throw new ResourceNotFoundException("Convênio com ID " + convenioId + " não encontrado.");
        }
        obj.setAditivos(aditivoRepository.findByConvenioIdIn(Collections.singletonList(convenioId)));
        obj.setLancamento(lancamentoConvenioRepository.findByConvenioIdIn(Collections.singletonList(convenioId)));
        return obj;
    }

}
