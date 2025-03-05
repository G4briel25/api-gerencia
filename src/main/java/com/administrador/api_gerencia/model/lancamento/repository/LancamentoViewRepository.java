package com.administrador.api_gerencia.model.lancamento.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.lancamento.LancamentoView;

import java.util.List;
import java.util.Optional;

public interface LancamentoViewRepository extends GenericRepository<LancamentoView, Long> {

    List<LancamentoView> findByConvenioId(Long convenioId);
    Optional<LancamentoView> findByIdAndConvenioId(Long id, Long convenioId);

}
