package com.administrador.api_gerencia.model.convenio.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioDetalhado;

public interface ConvenioDetalhadoRepository extends GenericRepository<ConvenioDetalhado, Long> {

    ConvenioDetalhado findConvenioDetalhadoById(Long convenioId);

}
