package com.aronith.vet.controller;

import com.aronith.vet.dto.request.RazaRequestDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.service.RazaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raza")
@AllArgsConstructor
public class RazaController {

    private final RazaService razaService;

    @PostMapping("/guardar")
    public ResponseEntity<RazaResponseDTO> guardarRaza(@RequestBody RazaRequestDTO dto) {
        RazaResponseDTO response = razaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RazaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(razaService.findAll());
    }

    @GetMapping("/buscar/{idEspecie}")
    public ResponseEntity<List<RazaResponseDTO>> buscarPorEspecieId(@PathVariable Long idEspecie) {
        List<RazaResponseDTO> razas = razaService.findByEspecieId(idEspecie);
        if (razas.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(razas);
    }

}
