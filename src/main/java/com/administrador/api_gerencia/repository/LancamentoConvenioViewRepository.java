package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.LancamentoConvenioView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LancamentoConvenioViewRepository extends GenericRepository<LancamentoConvenioView, Long> {

    @Query("SELECT l FROM LancamentoConvenioView l WHERE l.convenioId = :convenioId AND l.aditivoId IS NULL ORDER BY l.id DESC")
    List<LancamentoConvenioView> findByConvenioIdAndAditivoIdIsNullOrderedByIdDesc(Long convenioId);

    List<LancamentoConvenioView> findByConvenioIdIn(List<Long> convenioIds);

    Optional<LancamentoConvenioView> findByIdAndConvenioId(Long id, Long convenioId);

}
