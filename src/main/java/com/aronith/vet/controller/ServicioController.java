package com.aronith.vet.controller;

import com.aronith.vet.dto.request.ServicioRequestDTO;
import com.aronith.vet.dto.response.ServicioResponseDTO;
import com.aronith.vet.service.ServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
@AllArgsConstructor
@Tag(name = "Servicios", description = "Operaciones sobre servicios.")
public class ServicioController {

    private final ServicioService servicioService;

    @Operation(summary = "Guardar un nuevo servicio en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<ServicioResponseDTO> guardar(@RequestBody ServicioRequestDTO dto) {
        ServicioResponseDTO response = servicioService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obtener una lista de todos los servicios registrados")
    @GetMapping("/listar")
    public ResponseEntity<List<ServicioResponseDTO>> listar() {
        return ResponseEntity.ok(servicioService.listarTodos());
    }


}
