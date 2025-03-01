package com.administrador.api_gerencia.model.convenio.repository;

import com.administrador.api_gerencia.generic.GenericRepository;
import com.administrador.api_gerencia.model.convenio.ConvenioViewDetalhado;

public interface ConvenioViewDetalhadoRepository extends GenericRepository<ConvenioViewDetalhado, Long> {

    ConvenioViewDetalhado findConvenioDetalhadoById(Long convenioId);

}
