package com.example.demo.api.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPSDTO {
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer hour;
    private Integer minute;
    private Integer second;
}

