package com.gyl.CrudGyl.dto.request;

import jakarta.validation.constraints.NotNull;

public record VentaRequestDto(
        @NotNull(message = "El ID del cliente es obligatorio")
        Long cliente_id

) {
}