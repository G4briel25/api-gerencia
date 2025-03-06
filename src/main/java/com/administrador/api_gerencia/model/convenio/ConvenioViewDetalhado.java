package com.administrador.api_gerencia.model.convenio;

import com.administrador.api_gerencia.model.aditivo.Aditivo;
import com.administrador.api_gerencia.model.aditivo.AditivoView;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenio;
import com.administrador.api_gerencia.model.lancamento.convenio.LancamentoConvenioView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "convenio", schema = "vw_convenio")
public class ConvenioViewDetalhado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "numero_convenio")
    private String numeroConvenio;

    @NotBlank
    @Column(name = "objeto")
    private String objeto;

    @NotBlank
    @Column(name = "numero_processo")
    private String numeroProcesso;

    @NotBlank
    @Column(name = "proponente")
    private String proponente;

    @NotBlank
    @Column(name = "convenente")
    private String convenente;

    @NotBlank
    @Column(name = "responsaveis")
    private String responsaveis;

    @NotNull
    @Column(name = "data_inicio")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "data_fim")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataFim;

    @NotNull
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @NotBlank
    @Column(name = "tipo_de_convenio")
    private String tipoDeConvenio;

    @NotBlank
    @Column(name = "situacao_descricao")
    private String situacaoDescricao;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    private List<AditivoView> aditivos = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    private List<LancamentoConvenioView> lancamento = new ArrayList<>();

}
