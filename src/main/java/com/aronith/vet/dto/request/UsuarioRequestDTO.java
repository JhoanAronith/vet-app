package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para el registro o actualización de credenciales de usuario")
public record UsuarioRequestDTO(

        @Schema(description = "Nombre de usuario para el inicio de sesión", example = "jhoan.admin")
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String username,

        @Schema(description = "Contraseña de acceso (Se recomienda usar caracteres alfanuméricos)",
                example = "P@ssw0rd2026",
                type = "string",
                format = "password")
        @NotBlank(message = "La contraseña de usuario es obligatoria")
        String password,

        @Schema(description = "Correo electrónico institucional o personal", example = "jhoan@vetsystem.com")
        @NotBlank(message = "El email de usuario es obligatorio")
        String email
) {}
