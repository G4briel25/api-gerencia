package com.administrador.api_gerencia.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.ConvenioView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvenioViewRepository extends GenericRepository<ConvenioView, Long> {

    @Query("SELECT c FROM ConvenioView c ORDER BY c.id DESC")
    List<ConvenioView> findAllOrderedByIdDesc();

}
