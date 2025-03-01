package com.administrador.api_gerencia.model.aditivo.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.aditivo.Aditivo;

import java.util.List;

public interface AditivoRepository extends GenericRepository<Aditivo, Long> {

    List<Aditivo> findByConvenioIdIn(List<Long> convenioIds);

}
