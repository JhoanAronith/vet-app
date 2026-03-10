package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con la información detallada del cliente")
public record ClienteResponseDTO(

        @Schema(description = "Identificador único del cliente en el sistema", example = "1")
        Long id,

        @Schema(description = "Nombre(s) del cliente", example = "Jhoan")
        String nombre,

        @Schema(description = "Apellido(s) del cliente", example = "Aronith")
        String apellido,

        @Schema(description = "Documento Nacional de Identidad", example = "70654321")
        String dni,

        @Schema(description = "Número de contacto telefónico", example = "987654321")
        String telefono,

        @Schema(description = "Correo electrónico registrado", example = "jhoan@example.com")
        String email,

        @Schema(description = "Dirección de residencia del cliente", example = "Av. Principal 123, Lima")
        String direccion
) { }
