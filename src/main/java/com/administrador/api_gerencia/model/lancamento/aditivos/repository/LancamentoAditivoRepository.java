package com.administrador.api_gerencia.model.lancamento.aditivos.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.lancamento.aditivos.LancamentoAditivo;

import java.util.Collection;
import java.util.List;

public interface LancamentoAditivoRepository extends GenericRepository<LancamentoAditivo, Long> {

    List<LancamentoAditivo> findByAditivoId(Long aditivoId);
    List<LancamentoAditivo> findByAditivoIdIn(Collection<Long> aditivoIds);

}
