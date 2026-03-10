package com.aronith.vet.controller;

import com.aronith.vet.dto.request.EspecieRequestDTO;
import com.aronith.vet.dto.response.EspecieResponseDTO;
import com.aronith.vet.service.EspecieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especie")
@AllArgsConstructor
@Tag(name = "Especies", description = "Operaciones sobre especies.")
public class EspecieController {

    private final EspecieService especieService;

    @Operation(summary = "Guardar una nueva especie en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<EspecieResponseDTO> guardarEspecie(@RequestBody EspecieRequestDTO dto) {
        EspecieResponseDTO response = especieService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obtener una lista de todas las especies")
    @GetMapping("/listar")
    public ResponseEntity<List<EspecieResponseDTO>> listarEspecies() {
        return ResponseEntity.ok(especieService.listarTodos());
    }

}
