package com.aronith.vet.controller;

import com.aronith.vet.dto.request.UsuarioRequestDTO;
import com.aronith.vet.dto.response.UsuarioResponseDTO;
import com.aronith.vet.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Registrar un nuevo usuario en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<UsuarioResponseDTO> guardarUsuario(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO response = usuarioService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Buscar un usuario por su email")
    @GetMapping("/buscar/{email}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable String email) {
        return usuarioService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
