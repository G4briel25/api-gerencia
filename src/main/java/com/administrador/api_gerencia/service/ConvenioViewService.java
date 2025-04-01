package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.ConvenioViewFilter;
import com.administrador.api_gerencia.model.ConvenioView;
import com.administrador.api_gerencia.repository.ConvenioViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvenioViewService extends GenericService<ConvenioView, Long> {

    private final ConvenioViewRepository convenioViewRepository;

    public ConvenioViewService(ConvenioViewRepository repository) {
        super(repository);
        this.convenioViewRepository = repository;
    }

    public List<ConvenioView> listar() {
        return convenioViewRepository.findAllOrderedByIdDesc();
    }

    public List<ConvenioView> buscarConvenios(ConvenioViewFilter filtro) {
        List<ConvenioView> convenios = convenioViewRepository.findAll();

        return convenios.stream()
                .filter(c -> filtro.getProponente() == null || c.getProponente().toLowerCase().contains(filtro.getProponente().toLowerCase()))
                .filter(c -> filtro.getConvenente() == null || c.getConvenente().toLowerCase().contains(filtro.getConvenente().toLowerCase()))
                .filter(c -> filtro.getResponsaveis() == null || c.getResponsaveis().toLowerCase().contains(filtro.getResponsaveis().toLowerCase()))
                .filter(c -> filtro.getObjeto() == null || c.getObjeto().toLowerCase().contains(filtro.getObjeto().toLowerCase()))
                .filter(c -> filtro.getNumeroConvenio() == null || c.getNumeroConvenio().equals(filtro.getNumeroConvenio()))
                .filter(c -> filtro.getNumeroProcesso() == null || c.getNumeroProcesso().equals(filtro.getNumeroProcesso()))
                .collect(Collectors.toList());
    }

}
