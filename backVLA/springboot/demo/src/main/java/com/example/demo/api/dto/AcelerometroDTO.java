package com.example.demo.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcelerometroDTO {
    private Float accX;
    private Float accY;
    private Float accZ;

    private Float gyroX;
    private Float gyroY;
    private Float gyroZ;

    private Float temp;
    private Float roll;
    private Float pitch;
}
