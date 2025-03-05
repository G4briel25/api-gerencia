package com.administrador.api_gerencia.model.lancamento.aditivos.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.lancamento.aditivos.LancamentoAditivoView;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenioView;

import java.util.List;
import java.util.Optional;

public interface LancamentoAditivoViewRepository extends GenericRepository<LancamentoAditivoView, Long> {

    List<LancamentoAditivoView> findByConvenioIdAndAditivoId(Long convenioId, Long aditivoId);

    Optional<LancamentoAditivoView> findByIdAndConvenioIdAndAditivoId(Long id, Long convenioId, Long aditivoId);

}
