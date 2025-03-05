package com.administrador.api_gerencia.model.lancamento;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
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
@Table(name = "lancamento", schema = "dm_convenio")
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_repasse")
    private LocalDate dataRepasse;

    @NotNull
    @Column(name = "exercicio")
    private Integer exercicio;

    @NotNull
    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "convenio_id", nullable = false)
    private Long convenioId;

    @Column(name = "aditivo_id")
    private Long aditivoId;

}
