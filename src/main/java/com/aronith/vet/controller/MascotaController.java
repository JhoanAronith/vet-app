package com.aronith.vet.controller;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.dto.response.ClienteResumenDto;
import com.aronith.vet.dto.response.MascotaResponseDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.service.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascota")
@AllArgsConstructor
@Tag(name = "Mascotas", description = "Operaciones sobre mascotas.")
public class MascotaController {

    private final MascotaService mascotaService;

    @Operation(summary = "Guardar una mascota en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<MascotaResponseDTO> guardar(@Valid @RequestBody MascotaRequestDTO dto) {
        MascotaResponseDTO response = mascotaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Operation(summary = "Obtener una lista de todas las mascotas registradas")
    @GetMapping("/listar")
    public ResponseEntity<List<MascotaResponseDTO>> listarMascotas() {
        return ResponseEntity.ok(mascotaService.listarTodos());
    }

    @Operation(summary = "Buscar una mascota por su nombre")
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<MascotaResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<MascotaResponseDTO> mascotas = mascotaService.buscarPorNombre(nombre);
        if (mascotas.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @Operation(summary = "Obtener el dueño de una mascota por su id")
    @GetMapping("/buscar/cliente/{idMascota}")
    public ResponseEntity<ClienteResumenDto> buscarClientePorIdMascota(@PathVariable Long idMascota) {
        return mascotaService.buscarClientePorMascotaId(idMascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
