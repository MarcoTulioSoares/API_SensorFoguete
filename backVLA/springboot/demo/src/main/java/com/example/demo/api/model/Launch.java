package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_LAUNCHES")
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "launchSequence")
    @SequenceGenerator(name = "launchSequence", sequenceName = "SEQ_LAUNCHES", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LAUNCH_DATE")
    private Long launchDate;
}