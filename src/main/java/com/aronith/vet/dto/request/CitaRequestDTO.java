package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "DTO para la solicitud de creación de una nueva cita médica")
public record CitaRequestDTO (

        @Schema(
                description = "Fecha y hora programada para la cita (Formato ISO)",
                example = "2026-05-20T10:30:00"
        )
        @NotNull @Future
        LocalDateTime fechaHora,

        @Schema(
                description = "Breve descripción del motivo de la cita",
                example = "Control post-operatorio y cambio de vendajes",
                minLength = 5,
                maxLength = 255
        )
        @NotBlank
        String motivo,

        @Schema(
                description = "ID único de la mascota que recibirá la atención",
                example = "1"
        )
        @NotNull
        Long idMascota
){ }
