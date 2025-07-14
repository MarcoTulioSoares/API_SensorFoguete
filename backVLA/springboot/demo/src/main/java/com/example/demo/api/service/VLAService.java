package com.example.demo.api.service;

import com.example.demo.api.dto.VLADTO;
import com.example.demo.api.model.LancamentoEntity;
import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.repository.LancamentoRepository;
import com.example.demo.api.repository.VLARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class VLAService {

    private final VLARepository sensorRepository;
    private final LancamentoRepository lancamentoRepository;
    private Integer idSensorInicial = null;
    private Integer idSensorFinal = null;


    private final RestTemplate restTemplate = new RestTemplate(); // ideal: injetar com @Bean

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> task;
    //private static final String SENSOR_URL = "http://localhost:8080/json";
    private static final String SENSOR_URL = "http://192.168.4.1/json";

    // Inicia o agendamento
    public void startScheduler() {
        if (task == null || task.isCancelled()) {
            idSensorInicial = null;
            idSensorFinal = null;
            task = scheduler.scheduleAtFixedRate(this::fetchAndSaveSensorData, 0, 100, TimeUnit.MILLISECONDS);
            System.out.println("Scheduler iniciado manualmente.");
        }
    }


    // Encerra o agendamento
    public void stopScheduler() {
        if (task != null && !task.isCancelled()) {
            task.cancel(true);
            System.out.println("Scheduler parado.");

            if (idSensorInicial != null && idSensorFinal != null) {
                LancamentoEntity lancamento = LancamentoEntity.builder()
                        .idSensorInicial(idSensorInicial)
                        .idSensorFinal(idSensorFinal)
                        .build();

                lancamentoRepository.save(lancamento);
                System.out.println("Lançamento salvo com sucesso: " + lancamento);
            } else {
                System.out.println("IDs do lançamento não definidos, não foi possível salvar.");
            }
        }
    }


    public boolean isSchedulerRunning() {
        return task != null && !task.isCancelled();
    }

    public void fetchAndSaveSensorData() {
        try {
            VLADTO dto = restTemplate.getForObject(SENSOR_URL, VLADTO.class);
            var sensors = dto.getSensors();

            System.out.println("DADOS RECEBIDOS:");
            System.out.println("Timestamp: " + sensors.getTimestamp());
            System.out.println("Altitude (Altímetro): " + sensors.getAltimetro().getAltitude());

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
                    .timestamp(sensors.getTimestamp().floatValue())
                    .build();


            VLAEntity savedEntity = sensorRepository.save(entity);

            if (idSensorInicial == null) {
                idSensorInicial = savedEntity.getId();
            }
            idSensorFinal = savedEntity.getId();
        } catch (Exception e) {
            System.err.println("Erro ao buscar ou salvar dados do sensor: " + e.getMessage());
        }
    }
}
