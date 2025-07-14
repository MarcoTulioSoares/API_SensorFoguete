package com.example.demo.api.repository;

import com.example.demo.api.model.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<LancamentoEntity, Integer> {
}
