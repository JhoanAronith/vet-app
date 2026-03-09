package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para el registro o actualización de credenciales de usuario")
public record UsuarioRequestDTO(

        @Schema(description = "Nombre de usuario para el inicio de sesión", example = "jhoan.admin")
        String username,

        @Schema(description = "Contraseña de acceso (Se recomienda usar caracteres alfanuméricos)",
                example = "P@ssw0rd2026",
                type = "string",
                format = "password")
        String password,

        @Schema(description = "Correo electrónico institucional o personal", example = "jhoan@vetsystem.com")
        String email
) {}
