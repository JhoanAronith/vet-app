package com.aronith.vet.controller;

import com.aronith.vet.dto.request.CitaRequestDTO;
import com.aronith.vet.dto.response.CitaResponseDTO;
import com.aronith.vet.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cita")
@AllArgsConstructor
@Tag(name = "Citas", description = "Operaciones sobre citas.")
public class CitaController {

    private final CitaService citaService;

    @Operation(summary = "Guardar una nueva cita en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<CitaResponseDTO> guardar(@RequestBody CitaRequestDTO dto) {
        CitaResponseDTO response = citaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar todas las citas registradas en la base de datos")
    @GetMapping("/listar")
    public ResponseEntity<List<CitaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(citaService.obtenerTodos());
    }

    @Operation(summary = "Obtener una lista de citas por fecha")
    @GetMapping("/buscar")
    public ResponseEntity<List<CitaResponseDTO>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        List<CitaResponseDTO> citas = citaService.obtenerPorFecha(fecha);
        if (citas.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(citas);
    }

    @Operation(summary = "Obtener el historial de citas de una mascota")
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<CitaResponseDTO>> obtenerHistorial(@PathVariable Long idMascota) {
        List<CitaResponseDTO> historial = citaService.obtenerHistorialPorMascota(idMascota);
        return historial.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(historial);
    }

    @Operation(summary = "Obtener una lista de citas según su estado")
    @GetMapping("/estado")
    public ResponseEntity<List<CitaResponseDTO>> buscarPorEstado(@RequestParam(name = "valor") String estado) {
        List<CitaResponseDTO> citas = citaService.obtenerPorEstado(estado.toUpperCase());
        return citas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(citas);
    }

    @Operation(summary = "Cancelar una cita (CANCELLED)")
    @PatchMapping("/{idCita}/cancelar")
    public ResponseEntity<CitaResponseDTO> cancelar(@PathVariable Long idCita) {
        CitaResponseDTO citaCancelada = citaService.cancelarCita(idCita);
        return ResponseEntity.ok(citaCancelada);
    }

}
