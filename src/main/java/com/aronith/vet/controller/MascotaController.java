package com.aronith.vet.controller;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.dto.response.ClienteResumenDto;
import com.aronith.vet.dto.response.MascotaResponseDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascota")
@AllArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping("/guardar")
    public ResponseEntity<MascotaResponseDTO> guardar(@RequestBody MascotaRequestDTO dto) {
        MascotaResponseDTO response = mascotaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/listar")
    public ResponseEntity<List<MascotaResponseDTO>> listarMascotas() {
        return ResponseEntity.ok(mascotaService.listarTodos());
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<MascotaResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<MascotaResponseDTO> mascotas = mascotaService.buscarPorNombre(nombre);
        if (mascotas.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/buscar/cliente/{idMascota}")
    public ResponseEntity<ClienteResumenDto> buscarClientePorIdMascota(@PathVariable Long idMascota) {
        return mascotaService.buscarClientePorMascotaId(idMascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
