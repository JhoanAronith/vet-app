package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar o actualizar un cliente")
public record ClienteRequestDTO(

        @Schema(description = "Nombre del cliente", example = "Juan Carlos")
        @NotBlank @Size(min = 2, max = 50)
        String nombre,

        @Schema(description = "Apellidos del cliente", example = "Pérez García")
        @NotBlank @Size(min = 2, max = 50)
        String apellido,

        @Schema(description = "Documento Nacional de Identidad", example = "70654321")
        @NotBlank @Size(min = 8, max = 15)
        String dni,

        @Schema(description = "Número de teléfono de contacto", example = "987654321")
        @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Formato de teléfono inválido")
        String telefono,

        @Schema(description = "Correo electrónico de contacto", example = "juan.perez@example.com")
        @Email
        String email,

        @Schema(description = "Dirección domiciliaria", example = "Av. Principal 123, Lima")
        String direccion
) {}
