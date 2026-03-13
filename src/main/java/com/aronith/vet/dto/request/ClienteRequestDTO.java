package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar o actualizar un cliente")
public record ClienteRequestDTO(

        @Schema(description = "Nombre del cliente", example = "Juan Carlos")
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(description = "Apellidos del cliente", example = "Pérez García")
        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
        String apellido,

        @Schema(description = "Documento Nacional de Identidad", example = "70654321")
        @NotBlank(message = "El dni es obligatorio")
        @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
        String dni,

        @Schema(description = "Número de teléfono de contacto", example = "987654321")
        @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Formato de teléfono inválido")
        @Size(min = 9, max = 9, message = "El teléfono debe tener 9 números")
        String telefono,

        @Schema(description = "Correo electrónico de contacto", example = "juan.perez@example.com")
        @NotBlank(message = "El email es obligatorio")
        @Email
        String email,

        @Schema(description = "Dirección domiciliaria", example = "Av. Principal 123, Lima")
        String direccion
) {}
