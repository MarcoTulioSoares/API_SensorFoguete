package com.example.demo.api.service;

import com.example.demo.api.dto.VLADTO;
import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.repository.VLARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class VLAService {

    private final VLARepository sensorRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String SENSOR_URL = "http://localhost:8080/json";

    @Scheduled(fixedRate = 100)
    public void fetchAndSaveSensorData() {
        System.out.println("Scheduler iniciado");

        try {
            VLADTO dto = restTemplate.getForObject(SENSOR_URL, VLADTO.class);
            System.out.println(dto);

            if (dto != null && dto.getSensors() != null
                    && dto.getSensors().getAltimetro() != null
                    && dto.getSensors().getAcelerometro() != null) {

                System.out.println("DADOS RECEBIDOS:");
                System.out.println("Timestamp: " + dto.getTimestamp());
                System.out.println("Altitude: " + dto.getSensors().getAltimetro().getAltitude());
                System.out.println("Pressão: " + dto.getSensors().getAltimetro().getPressure());
                System.out.println("AccX: " + dto.getSensors().getAcelerometro().getAccX());
                System.out.println("AccY: " + dto.getSensors().getAcelerometro().getAccY());
                System.out.println("AccZ: " + dto.getSensors().getAcelerometro().getAccZ());
                System.out.println("GyroX: " + dto.getSensors().getAcelerometro().getGyroX());
                System.out.println("GyroY: " + dto.getSensors().getAcelerometro().getGyroY());
                System.out.println("GyroZ: " + dto.getSensors().getAcelerometro().getGyroZ());
                System.out.println("Temp: " + dto.getSensors().getAcelerometro().getTemp());
                System.out.println("Roll: " + dto.getSensors().getAcelerometro().getRoll());
                System.out.println("Pitch: " + dto.getSensors().getAcelerometro().getPitch());
                System.out.println("--------------------------------------");

                VLAEntity entity = VLAEntity.builder()
                        .altitude(dto.getSensors().getAltimetro().getAltitude())
                        .pressure(dto.getSensors().getAltimetro().getPressure())
                        .accX(dto.getSensors().getAcelerometro().getAccX())
                        .accY(dto.getSensors().getAcelerometro().getAccY())
                        .accZ(dto.getSensors().getAcelerometro().getAccZ())
                        .gyroX(dto.getSensors().getAcelerometro().getGyroX())
                        .gyroY(dto.getSensors().getAcelerometro().getGyroY())
                        .gyroZ(dto.getSensors().getAcelerometro().getGyroZ())
                        .temp(dto.getSensors().getAcelerometro().getTemp())
                        .roll(dto.getSensors().getAcelerometro().getRoll())
                        .pitch(dto.getSensors().getAcelerometro().getPitch())
                        .timestamp(dto.getTimestamp())
                        .build();

                sensorRepository.save(entity);
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar ou salvar dados do sensor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
