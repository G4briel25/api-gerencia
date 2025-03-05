package com.administrador.api_gerencia.model.aditivo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "aditivo", schema = "vw_convenio")
public class AditivoView implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_aditivo")
    private String numeroAditivo;

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

    @Column(name = "valor_total_aditivo")
    private BigDecimal valorTotalAditivo;

    @Column(name = "situacao_descricao_aditivo")
    private String situacaoDescricaoAditivo;

    @Column(name = "convenio_id", nullable = false)
    private Long convenioId;

}
