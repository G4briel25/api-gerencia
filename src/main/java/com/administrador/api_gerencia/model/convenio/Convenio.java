package com.administrador.api_gerencia.model.convenio;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.lancamento.Lancamento;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "convenio", schema = "dm_convenio")
public class Convenio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numeroConvenio;

    @NotBlank
    private String objeto;

    @NotBlank
    private String numeroProcesso;

    @NotBlank
    private String proponente;

    @NotBlank
    private String convenente;

    @NotBlank
    private String responsaveis;

    @NotNull
    private Date dataInicio;

    @NotNull
    private Date dataFim;

    @NotNull
    private BigDecimal valorTotal;

    @NotBlank
    private String tipoDeConvenio;

    @NotBlank
    private String situacaoDescricao;

    @OneToMany(mappedBy = "convenioId", orphanRemoval = true)
    private List<Aditivo> aditivos = new ArrayList<>();

    @OneToMany(mappedBy = "convenioId", orphanRemoval = true)
    private List<Lancamento> lancamentos = new ArrayList<>();

}
