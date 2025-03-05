package com.administrador.api_gerencia.model.lancamento.convenio.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenioView;

import java.util.List;
import java.util.Optional;

public interface LancamentoConvenioViewRepository extends GenericRepository<LancamentoConvenioView, Long> {

    List<LancamentoConvenioView> findByConvenioId(Long convenioId);
    Optional<LancamentoConvenioView> findByIdAndConvenioId(Long id, Long convenioId);

}
