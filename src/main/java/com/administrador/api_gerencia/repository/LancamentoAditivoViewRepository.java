package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.LancamentoAditivoView;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LancamentoAditivoViewRepository extends GenericRepository<LancamentoAditivoView, Long> {


    List<LancamentoAditivoView> findByAditivoId(Long aditivoId);
    List<LancamentoAditivoView> findByAditivoIdIn(Collection<Long> aditivoIds);
    List<LancamentoAditivoView> findByConvenioIdAndAditivoId(Long convenioId, Long aditivoId);
    Optional<LancamentoAditivoView> findByIdAndConvenioIdAndAditivoId(Long id, Long convenioId, Long aditivoId);

}
