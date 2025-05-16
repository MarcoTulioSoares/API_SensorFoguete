package com.example.demo.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VLADTO {
    private SensorsDTO sensors;
    private Long timestamp;
}
