package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con la información pública y de contacto del usuario")
public record UsuarioResponseDTO(

        @Schema(description = "Nombre de usuario único en el sistema", example = "jhoan.admin")
        String username,

        @Schema(description = "Correo electrónico institucional o personal vinculado", example = "jhoan@vetsystem.com")
        String email
) { }
