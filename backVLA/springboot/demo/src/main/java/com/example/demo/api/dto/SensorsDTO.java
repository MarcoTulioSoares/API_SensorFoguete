package com.example.demo.api.dto;

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
}
