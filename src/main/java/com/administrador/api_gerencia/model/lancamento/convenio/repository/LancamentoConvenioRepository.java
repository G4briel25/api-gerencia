package com.administrador.api_gerencia.model.lancamento.convenio.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenio;

import java.util.List;

public interface LancamentoConvenioRepository extends GenericRepository<LancamentoConvenio, Long> {

    List<LancamentoConvenio> findByConvenioIdIn(List<Long> convenioIds);

}
