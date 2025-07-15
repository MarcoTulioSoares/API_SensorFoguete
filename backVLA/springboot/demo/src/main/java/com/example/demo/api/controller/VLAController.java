package com.example.demo.api.controller;

import com.example.demo.api.model.LancamentoEntity;
import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.repository.LancamentoRepository;
import com.example.demo.api.service.VLAService;
import com.example.demo.api.service.VLAServiceV2;
import com.example.demo.api.dto.LancamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;


@RestController
@RequestMapping("/api/VLA")
public class VLAController {


    @Autowired
    private VLAService vlaService;

    @Autowired
    private VLAServiceV2 vlaServiceV2;

    @PostMapping("/start")
    public ResponseEntity<?> startScheduler() {
        LancamentoEntity lancamento = vlaService.startSchedulerAndReturnLancamento();
        return ResponseEntity.ok(java.util.Collections.singletonMap("idLancamento", lancamento.getIdLancamento()));
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
    public ResponseEntity<List<LancamentoDTO>> listarTodosLancamentos() {
        List<LancamentoEntity> lista = vlaServiceV2.listarTodosLancamentos();
        List<LancamentoDTO> dtoList = lista.stream()
            .map(l -> new LancamentoDTO(l.getIdLancamento(), l.getNome(), l.getDataLancamento()))
            .toList();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/deleta-lancamento/{id}")
    public ResponseEntity<String> excluirLancamento(@PathVariable int id) {
        boolean excluido = vlaServiceV2.excluirLancamentoPorId(id);
        if (excluido) {
            return ResponseEntity.ok("Lançamento e sensores associados excluídos com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lancamento-id/{id}")
    public ResponseEntity<LancamentoEntity> buscarLancamentoPorId(@PathVariable int id) {
        return vlaServiceV2.buscarLancamentoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public static class UpdateLancamentoRequest {
        public String nome;
        public Date dataLancamento;
    }

    @PutMapping("/lancamento-id/{id}")
    public ResponseEntity<LancamentoEntity> atualizarLancamento(@PathVariable int id, @RequestBody UpdateLancamentoRequest req) {
        Optional<LancamentoEntity> lancamentoOpt = vlaServiceV2.buscarLancamentoPorId(id);
        if (lancamentoOpt.isPresent()) {
            LancamentoEntity lancamento = lancamentoOpt.get();
            lancamento.setNome(req.nome);
            lancamento.setDataLancamento(req.dataLancamento);
            vlaServiceV2.salvarLancamento(lancamento);
            return ResponseEntity.ok(lancamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

