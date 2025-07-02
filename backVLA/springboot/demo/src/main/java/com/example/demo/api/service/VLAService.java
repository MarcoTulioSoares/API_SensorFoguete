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
    private final RestTemplate restTemplate = new RestTemplate(); // ideal seria injetar via @Bean

    //private static final String SENSOR_URL = "http://192.168.4.1/json"; // adicione http://
    private static final String SENSOR_URL = "http://localhost:8080/json";

    @Scheduled(fixedRate = 100)
    public void fetchAndSaveSensorData() {
        System.out.println("Scheduler iniciado");

        try {
            VLADTO dto = restTemplate.getForObject(SENSOR_URL, VLADTO.class);



            var sensors = dto.getSensors();



            System.out.println("DADOS RECEBIDOS:");
            System.out.println("Timestamp: " + dto.getTimestamp());
            System.out.println("Altitude (Altímetro): " + sensors.getAltimetro().getAltitude());
            System.out.println("Pressão: " + sensors.getAltimetro().getPressure());
            System.out.println("AccX: " + sensors.getAcelerometro().getAccX());
            // você pode imprimir mais campos se quiser

            VLAEntity entity = VLAEntity.builder()
                    .altitude(sensors.getAltimetro().getAltitude())
                    .pressure(sensors.getAltimetro().getPressure())
                    .accX(sensors.getAcelerometro().getAccX())
                    .accY(sensors.getAcelerometro().getAccY())
                    .accZ(sensors.getAcelerometro().getAccZ())
                    .gyroX(sensors.getAcelerometro().getGyroX())
                    .gyroY(sensors.getAcelerometro().getGyroY())
                    .gyroZ(sensors.getAcelerometro().getGyroZ())
                    .temp(sensors.getAcelerometro().getTemp())
                    .roll(sensors.getAcelerometro().getRoll())
                    .pitch(sensors.getAcelerometro().getPitch())
                    .voltageBase(sensors.getTensao().getVoltage_base().floatValue())
                    .voltageRocket(sensors.getTensao().getVoltage_rocket().floatValue())
                    .gpsLatitude(sensors.getGps().getLatitude().floatValue())
                    .gpsLongitude(sensors.getGps().getLongitude().floatValue())
                    .gpsAltitude(sensors.getGps().getAltitude().floatValue())
                    .gpsDay(sensors.getGps().getDay())
                    .gpsMonth(sensors.getGps().getMonth())
                    .gpsYear(sensors.getGps().getYear())
                    .gpsHour(sensors.getGps().getHour())
                    .gpsMinute(sensors.getGps().getMinute())
                    .gpsSecond(sensors.getGps().getSecond())
                    .espNowChannel(sensors.getEsp_now_channel())
                    .macAddress(sensors.getMac_address())
                    .timestamp(dto.getTimestamp().floatValue())
                    .build();


            sensorRepository.save(entity);
            System.out.println("Dados salvos com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao buscar ou salvar dados do sensor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
