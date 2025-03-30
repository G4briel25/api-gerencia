package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.LancamentoAditivoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface LancamentoAditivoViewRepository extends GenericRepository<LancamentoAditivoView, Long> {

    List<LancamentoAditivoView> findByAditivoId(Long aditivoId);
    List<LancamentoAditivoView> findByAditivoIdIn(Collection<Long> aditivoIds);

    @Query("SELECT l FROM LancamentoAditivoView l WHERE l.convenioId = :convenioId AND l.aditivoId = :aditivoId ORDER BY l.id DESC")
    List<LancamentoAditivoView> findByConvenioIdAndAditivoIdOrderedByIdDesc(Long convenioId, Long aditivoId);

    Optional<LancamentoAditivoView> findByIdAndConvenioIdAndAditivoId(Long id, Long convenioId, Long aditivoId);

}
