package com.example.demo.api.controller;

import com.example.demo.api.model.VLAEntity;
import com.example.demo.api.service.VLAServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/VLA")
public class VLAController {

    @Autowired
    VLAServiceV2 vlaServiceV2;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<VLAEntity>> listarTodos() {
        List<VLAEntity> lista = vlaServiceV2.listarTodos();
        return ResponseEntity.ok(lista);
    }

}
