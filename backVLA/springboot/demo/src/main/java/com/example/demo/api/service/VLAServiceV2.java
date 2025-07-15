package com.example.demo.api.service;

import com.example.demo.api.model.LancamentoEntity;
import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.repository.LancamentoRepository;
import com.example.demo.api.repository.VLARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VLAServiceV2 {

    private final VLARepository sensorRepository;

    public List<VLAEntity> listarTodos() {
        return sensorRepository.findAll();
    }

    private final LancamentoRepository lancamentoRepository;

    public List<LancamentoEntity> listarTodosLancamentos() {
        return lancamentoRepository.findAll();
    }

    public Optional<LancamentoEntity> buscarLancamentoPorId(int id) {
        return lancamentoRepository.findById(id);
    }

    public boolean excluirLancamentoPorId(int id) {
        Optional<LancamentoEntity> lancamento = lancamentoRepository.findById(id);
        if (lancamento.isPresent()) {
            lancamentoRepository.delete(lancamento.get()); // Exclusão em cascata
            return true;
        }
        return false;
    }
}
