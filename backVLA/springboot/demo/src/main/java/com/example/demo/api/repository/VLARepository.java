package com.example.demo.api.repository;

import com.example.demo.api.model.VLAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VLARepository extends JpaRepository<VLAEntity, Integer> {
}
