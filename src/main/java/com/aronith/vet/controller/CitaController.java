package com.aronith.vet.controller;

import com.aronith.vet.dto.request.CitaRequestDTO;
import com.aronith.vet.dto.response.CitaResponseDTO;
import com.aronith.vet.service.CitaService;
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
public class CitaController {

    private final CitaService citaService;

    @PostMapping("/guardar")
    public ResponseEntity<CitaResponseDTO> guardar(@RequestBody CitaRequestDTO dto) {
        CitaResponseDTO response = citaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CitaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(citaService.obtenerTodos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CitaResponseDTO>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fecha
    ) {
       List<CitaResponseDTO> citas = citaService.obtenerPorFecha(fecha);
       if (citas.isEmpty()) {
           return ResponseEntity.ok().build();
       }
       return ResponseEntity.ok(citas);
    }

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<CitaResponseDTO>> obtenerHistorial(@PathVariable Long idMascota) {
        List<CitaResponseDTO> historial = citaService.obtenerHistorialPorMascota(idMascota);
        return historial.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(historial);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<CitaResponseDTO>> buscarPorEstado(@RequestParam(name = "valor") String estado) {
        List<CitaResponseDTO> citas = citaService.obtenerPorEstado(estado.toUpperCase());
        return citas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(citas);
    }

    @PatchMapping("/{idCita}/cancelar")
    public ResponseEntity<CitaResponseDTO> cancelar(@PathVariable Long idCita) {
        CitaResponseDTO citaCancelada = citaService.cancelarCita(idCita);
        return ResponseEntity.ok(citaCancelada);
    }

}
