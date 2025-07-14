package com.example.demo.api.controller;

import com.example.demo.api.model.LancamentoEntity;
import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.service.VLAService;
import com.example.demo.api.service.VLAServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/VLA")
public class VLAController {

    @Autowired
    private VLAService vlaService;

    @Autowired
    private VLAServiceV2 vlaServiceV2;

    @PostMapping("/start")
    public ResponseEntity<String> startScheduler() {
        vlaService.startScheduler();
        return ResponseEntity.ok("Scheduler iniciado");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopScheduler() {
        vlaService.stopScheduler();
        return ResponseEntity.ok("Scheduler parado");
    }

    @GetMapping("/status")
    public ResponseEntity<String> getSchedulerStatus() {
        return ResponseEntity.ok(vlaService.isSchedulerRunning() ? "Rodando" : "Parado");
    }
    @GetMapping(value = "/listar")
    public ResponseEntity<List<VLAEntity>> listarTodos() {
        List<VLAEntity> lista = vlaServiceV2.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/listarLancamentos")
    public ResponseEntity<List<LancamentoEntity>> listarTodosLancamentos() {
        List<LancamentoEntity> lista = vlaServiceV2.listarTodosLancamentos();
        return ResponseEntity.ok(lista);
    }
}

