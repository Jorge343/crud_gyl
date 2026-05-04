package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record VentaRequestDto(
        @NotNull(message = "El ID del cliente es obligatorio")
        Long cliente_id

) {
}