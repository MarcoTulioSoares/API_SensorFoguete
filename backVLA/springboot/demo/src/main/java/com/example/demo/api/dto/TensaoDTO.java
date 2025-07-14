package com.example.demo.api.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TensaoDTO {
    private Double voltage_base;
    private Double voltage_rocket;
}

