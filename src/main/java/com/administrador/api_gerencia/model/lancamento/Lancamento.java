package com.administrador.api_gerencia.model.lancamento;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.convenio.Convenio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lancamento", schema = "dm_convenio")
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private Date dataRepasse;

    @NotNull
    private Integer exercicio;

    @NotNull
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "convenio_id", nullable = false)
    private Convenio convenioId;

    @ManyToOne
    @JoinColumn(name = "aditivo_id")
    private Aditivo aditivoId;

}
