package com.administrador.api_gerencia.model.aditivo.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.aditivo.AditivoView;

import java.util.List;
import java.util.Optional;

public interface AditivoViewRepository extends GenericRepository<AditivoView, Long> {

    List<AditivoView> findByConvenioIdIn(List<Long> convenioIds);
    List<AditivoView> findByConvenioId(Long convenioId);
    Optional<AditivoView> findByIdAndConvenioId(Long id, Long convenioId);

}
