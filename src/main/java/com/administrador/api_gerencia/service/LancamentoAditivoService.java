package com.administrador.api_gerencia.service;

import com.administrador.api_gerencia.generic.GenericService;
import com.administrador.api_gerencia.model.LancamentoAditivo;
import com.administrador.api_gerencia.repository.LancamentoAditivoRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoAditivoService extends GenericService<LancamentoAditivo, Long> {

    public LancamentoAditivoService(LancamentoAditivoRepository repository) {
        super(repository);
    }

}
