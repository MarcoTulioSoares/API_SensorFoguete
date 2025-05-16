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
}
