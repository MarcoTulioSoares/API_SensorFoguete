package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_LANCAMENTO")
public class LancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LancamentoSequence")
    @SequenceGenerator(name = "LancamentoSequence", sequenceName = "SEQ_LANCAMENTO", allocationSize = 1)
    @Column(name = "ID_LANCAMENTO")
    private Integer idLancamento;

    @Column(name = "ID_SENSOR_INICIAL")
    private Integer idSensorInicial;

    @Column(name = "ID_SENSOR_FINAL")
    private Integer idSensorFinal;


}
