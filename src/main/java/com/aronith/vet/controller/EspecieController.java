package com.aronith.vet.controller;

import com.aronith.vet.dto.request.EspecieRequestDTO;
import com.aronith.vet.dto.response.EspecieResponseDTO;
import com.aronith.vet.service.EspecieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especie")
@AllArgsConstructor
public class EspecieController {

    private final EspecieService especieService;

    @PostMapping("/guardar")
    public ResponseEntity<EspecieResponseDTO> guardarEspecie(@RequestBody EspecieRequestDTO dto) {
        EspecieResponseDTO response = especieService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EspecieResponseDTO>> listarEspecies() {
        return ResponseEntity.ok(especieService.listarTodos());
    }

}
