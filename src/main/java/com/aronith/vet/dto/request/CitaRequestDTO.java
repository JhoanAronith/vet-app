package com.aronith.vet.dto.request;

import java.time.LocalDateTime;

public record CitaRequestDTO (
        LocalDateTime fechaHora,
        String motivo,
        Long idMascota
){ }
