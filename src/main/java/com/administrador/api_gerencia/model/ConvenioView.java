package com.administrador.api_gerencia.model;

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

@Getter
@Setter
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "convenio", schema = "vw_convenio")
public class ConvenioView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_convenio")
    private String numeroConvenio;

    @Column(name = "objeto")
    private String objeto;

    @Column(name = "numero_processo")
    private String numeroProcesso;

    @Column(name = "proponente")
    private String proponente;

    @Column(name = "convenente")
    private String convenente;

    @NotBlank
    @Column(name = "responsaveis")
    private String responsaveis;

    @Column(name = "data_inicio")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataFim;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "tipo_de_convenio")
    private String tipoDeConvenio;

    @Column(name = "situacao_descricao")
    private String situacaoDescricao;

}
