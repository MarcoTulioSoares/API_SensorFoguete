package com.example.demo.api.service;

import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.repository.VLARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VLAServiceV2 {
    private final VLARepository sensorRepository;
    public List<VLAEntity> listarTodos() {
        return sensorRepository.findAll();
    }
}
