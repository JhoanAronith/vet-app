package com.aronith.vet.controller;

import com.aronith.vet.dto.request.RecetaRequestDTO;
import com.aronith.vet.dto.response.RecetaResponseDTO;
import com.aronith.vet.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receta")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping("/guardar")
    public ResponseEntity<RecetaResponseDTO> crear(@RequestBody RecetaRequestDTO request) {
        return new ResponseEntity<>(recetaService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/visita/{idVisita}")
    public ResponseEntity<RecetaResponseDTO> obtenerPorVisita(@PathVariable Long idVisita) {
        return ResponseEntity.ok(recetaService.obtenerPorVisita(idVisita));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

}