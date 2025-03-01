package com.administrador.api_gerencia.model.aditivo.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoRepository;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoViewRepository;
import org.springframework.stereotype.Service;

@Service
public class AditivoViewService extends GenericService<AditivoView, Long> {

    public AditivoViewService(AditivoViewRepository repository) {
        super(repository);
    }
}
