package com.administrador.api_gerencia.model.aditivo.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.repository.AditivoRepository;
import org.springframework.stereotype.Service;

@Service
public class AditivoService extends GenericService<Aditivo, Long> {

    public AditivoService(AditivoRepository repository) {
        super(repository);
    }
}
