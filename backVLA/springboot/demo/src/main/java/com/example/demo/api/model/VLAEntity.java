package com.example.demo.api.model;

import com.example.demo.api.model.Launch;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SENSORES")
public class VLAEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sensorSequence")
    @SequenceGenerator(name = "sensorSequence", sequenceName = "SEQ_SENSORES", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ALTITUDE")
    private Float altitude;

    @Column(name = "PRESSURE")
    private Float pressure;

    @Column(name = "ACC_X")
    private Float accX;

    @Column(name = "ACC_Y")
    private Float accY;

    @Column(name = "ACC_Z")
    private Float accZ;

    @Column(name = "GYRO_X")
    private Float gyroX;

    @Column(name = "GYRO_Y")
    private Float gyroY;

    @Column(name = "GYRO_Z")
    private Float gyroZ;

    @Column(name = "TEMP")
    private Float temp;

    @Column(name = "ROLL")
    private Float roll;

    @Column(name = "PITCH")
    private Float pitch;

    @Column(name = "TIMESTAMP")
    private Long timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LAUNCH_ID", nullable = false)
    private Launch launch;
}
