package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.LancamentoConvenioView;

import java.util.List;
import java.util.Optional;

public interface LancamentoConvenioViewRepository extends GenericRepository<LancamentoConvenioView, Long> {

    List<LancamentoConvenioView> findByConvenioIdIn(List<Long> convenioIds);
    List<LancamentoConvenioView> findByConvenioIdAndAditivoIdIsNull(Long convenioId);
    Optional<LancamentoConvenioView> findByIdAndConvenioId(Long id, Long convenioId);

}
