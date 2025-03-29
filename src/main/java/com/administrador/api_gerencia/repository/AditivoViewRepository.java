package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.AditivoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AditivoViewRepository extends GenericRepository<AditivoView, Long> {

    List<AditivoView> findByConvenioIdIn(List<Long> convenioIds);

    @Query("SELECT a FROM AditivoView a WHERE a.convenioId = :convenioId ORDER BY a.id DESC")
    List<AditivoView> findByConvenioIdOrderedByIdDesc(Long convenioId);

    Optional<AditivoView> findByIdAndConvenioId(Long id, Long convenioId);

}
