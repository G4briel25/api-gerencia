package com.administrador.api_gerencia.model.convenio.service;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoViewRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioViewDetalhado;
import com.administrador.api_gerencia.model.convenio.repository.ConvenioViewDetalhadoRepository;
import com.administrador.api_gerencia.model.lancamento.aditivos.LancamentoAditivoView;
import com.administrador.api_gerencia.model.lancamento.aditivos.repository.LancamentoAditivoViewRepository;
import com.administrador.api_gerencia.model.lancamento.convenio.repository.LancamentoConvenioViewRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConvenioDetalhadoService extends GenericService<ConvenioViewDetalhado, Long> {

    private final ConvenioViewDetalhadoRepository repository;
    private final AditivoViewRepository aditivoViewRepository;
    private final LancamentoConvenioViewRepository lancamentoConvenioViewRepository;
    private final LancamentoAditivoViewRepository lancamentoAditivoViewRepository;

    public ConvenioDetalhadoService(ConvenioViewDetalhadoRepository repository,
                                    AditivoViewRepository aditivoViewRepository,
                                    LancamentoConvenioViewRepository lancamentoConvenioViewRepository,
                                    LancamentoAditivoViewRepository lancamentoAditivoViewRepository
    ) {
        super(repository);
        this.repository = repository;
        this.aditivoViewRepository = aditivoViewRepository;
        this.lancamentoConvenioViewRepository = lancamentoConvenioViewRepository;
        this.lancamentoAditivoViewRepository = lancamentoAditivoViewRepository;
    }


    public ConvenioViewDetalhado buscarConvenioDetalhado(Long convenioId) {
        ConvenioViewDetalhado obj = repository.findConvenioDetalhadoById(convenioId);
        if (obj == null) {
            throw new ResourceNotFoundException("Convênio com ID " + convenioId + " não encontrado.");
        }

        // Buscar aditivos
        List<AditivoView> aditivos = aditivoViewRepository.findByConvenioIdIn(Collections.singletonList(convenioId));
        obj.setAditivos(aditivos);

        // Buscar lançamentos do convênio
        obj.setLancamento(lancamentoConvenioViewRepository.findByConvenioIdIn(Collections.singletonList(convenioId))
                .stream()
                .filter(lancamento -> lancamento.getAditivoId() == null)
                .toList()
        );

        // Buscar lançamentos dos aditivos
        if (!aditivos.isEmpty()) {
            List<Long> aditivoIds = aditivos.stream().map(AditivoView::getId).toList();
            Map<Long, List<LancamentoAditivoView>> lancamentosPorAditivo = lancamentoAditivoViewRepository.findByAditivoIdIn(aditivoIds)
                    .stream()
                    .collect(Collectors.groupingBy(LancamentoAditivoView::getAditivoId));

            // Atribuir os lançamentos aos aditivos
            aditivos.forEach(aditivo ->
                    aditivo.setLancamento(lancamentosPorAditivo.getOrDefault(aditivo.getId(), Collections.emptyList()))
            );
        }

        return obj;
    }

}
