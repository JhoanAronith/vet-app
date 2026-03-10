package com.aronith.vet.controller;

import com.aronith.vet.dto.request.VisitaRequestDTO;
import com.aronith.vet.dto.response.VisitaResponseDTO;
import com.aronith.vet.service.VisitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/visita")
@RequiredArgsConstructor
@Tag(name = "Visitas", description = "Operaciones sobre visitas.")
public class VisitaController {

    private final VisitaService visitaService;

    @Operation(summary = "Guardar una visita en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<VisitaResponseDTO> crear(@RequestBody VisitaRequestDTO request) {
        return new ResponseEntity<>(visitaService.crearVisita(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una visita por su id")
    @GetMapping("/{id}")
    public ResponseEntity<VisitaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.obtenerPorId(id));
    }

    @Operation(summary = "Obtener el historial de visitas de una mascota")
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<VisitaResponseDTO>> listarPorMascota(@PathVariable Long idMascota) {
        return ResponseEntity.ok(visitaService.listarPorMascota(idMascota));
    }

    @Operation(summary = "Obtener una lista de visitas entre dos fechas")
    @GetMapping("/fechas")
    public ResponseEntity<List<VisitaResponseDTO>> listarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(visitaService.listarPorRangoDeFechas(inicio, fin));
    }
}