package com.administrador.api_gerencia.model.lancamento.convenio;

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

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "lancamento", schema = "vw_convenio")
public class LancamentoConvenioView implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_repasse")
    private LocalDate dataRepasse;

    @Column(name = "exercicio")
    private Integer exercicio;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "convenio_id", nullable = false)
    private Long convenioId;

    @Column(name = "aditivo_id")
    private Long aditivoId;

}
