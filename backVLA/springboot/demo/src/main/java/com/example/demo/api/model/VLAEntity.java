package com.example.demo.api.model;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sensorSequence")
    @SequenceGenerator(name = "sensorSequence", sequenceName = "SEQ_SENSORES", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    // Altímetro
    @Column(name = "ALTITUDE")
    private Float altitude;

    @Column(name = "PRESSURE")
    private Float pressure;

    // Acelerômetro
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

    // Tensão
    @Column(name = "VOLTAGE_BASE")
    private Float voltageBase;

    @Column(name = "VOLTAGE_ROCKET")
    private Float voltageRocket;

    // GPS
    @Column(name = "GPS_LATITUDE")
    private Float gpsLatitude;

    @Column(name = "GPS_LONGITUDE")
    private Float gpsLongitude;

    @Column(name = "GPS_ALTITUDE")
    private Float gpsAltitude;

    @Column(name = "GPS_DAY")
    private Integer gpsDay;

    @Column(name = "GPS_MONTH")
    private Integer gpsMonth;

    @Column(name = "GPS_YEAR")
    private Integer gpsYear;

    @Column(name = "GPS_HOUR")
    private Integer gpsHour;

    @Column(name = "GPS_MINUTE")
    private Integer gpsMinute;

    @Column(name = "GPS_SECOND")
    private Integer gpsSecond;

    // Outros
    @Column(name = "ESP_NOW_CHANNEL")
    private Integer espNowChannel;

    @Column(name = "MAC_ADDRESS")
    private String macAddress;

    @Column(name = "TIMESTAMP")
    private Float timestamp;
}
