package com.aronith.vet.controller;

import com.aronith.vet.dto.request.RecetaRequestDTO;
import com.aronith.vet.dto.response.RecetaResponseDTO;
import com.aronith.vet.service.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receta")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "Operaciones sobre recetas.")
public class RecetaController {

    private final RecetaService recetaService;

    @Operation(summary = "Guardar una nueva receta en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<RecetaResponseDTO> crear(@Valid @RequestBody RecetaRequestDTO request) {
        return new ResponseEntity<>(recetaService.crear(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener la receta que pertenece a una visita dada")
    @GetMapping("/visita/{idVisita}")
    public ResponseEntity<RecetaResponseDTO> obtenerPorVisita(@PathVariable Long idVisita) {
        return ResponseEntity.ok(recetaService.obtenerPorVisita(idVisita));
    }

    @Operation(summary = "Obtener una receta por su id")
    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

}