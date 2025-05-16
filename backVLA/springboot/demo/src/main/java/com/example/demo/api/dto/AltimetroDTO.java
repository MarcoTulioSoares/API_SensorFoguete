package com.example.demo.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AltimetroDTO {
    private Float altitude;
    private Float pressure;
}
