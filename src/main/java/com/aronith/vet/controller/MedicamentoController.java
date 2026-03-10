package com.aronith.vet.controller;

import com.aronith.vet.dto.request.MedicamentoRequestDTO;
import com.aronith.vet.dto.response.MedicamentoResponseDTO;
import com.aronith.vet.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamento")
@AllArgsConstructor
@Tag(name = "Medicamentos", description = "Operaciones sobre medicamentos.")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @Operation(summary = "Guardar un nuevo medicamento en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<MedicamentoResponseDTO> guardar(@RequestBody MedicamentoRequestDTO dto) {
        MedicamentoResponseDTO response = medicamentoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obtener una lista de todos los medicamentos registrados")
    @GetMapping("/listar")
    public ResponseEntity<List<MedicamentoResponseDTO>> listar() {
        return ResponseEntity.ok(medicamentoService.listar());
    }

}
