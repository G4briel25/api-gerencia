package com.administrador.api_gerencia.model.lancamento.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.lancamento.Lancamento;

import java.util.List;

public interface LancamentoRepository extends GenericRepository<Lancamento, Long> {

    List<Lancamento> findByConvenioIdIn(List<Long> convenioIds);

}
