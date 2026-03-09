package com.aronith.vet.controller;

import com.aronith.vet.dto.request.ClienteRequestDTO;
import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.dto.response.MascotaResumenDTO;
import com.aronith.vet.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
@Tag(name = "Clientes", description = "Operaciones sobre clientes.")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/guardar")
    public ResponseEntity<ClienteResponseDTO> guardar(@RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO response = clienteService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/mascotas/{idCliente}")
    public ResponseEntity<List<MascotaResumenDTO>> listarMascotasCliente(@PathVariable Long idCliente) {
        List<MascotaResumenDTO> mascotas = clienteService.listarMascotasPorCliente(idCliente);
        if (mascotas.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(mascotas);
    }
}
