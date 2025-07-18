package com.example.demo.api.dto;

import com.example.demo.api.model.LancamentoEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorsDTO {
    private AltimetroDTO altimetro;
    private AcelerometroDTO acelerometro;
    private TensaoDTO tensao;
    private GPSDTO gps;
    private Integer esp_now_channel;
    private String mac_address;
    // `timestamp` do simulador vem como valor decimal (segundos com fração).
    // Usar Double para não perder a parte fracionária ao desserializar.
    private Double timestamp;
    private LancamentoEntity lancamento;
}
