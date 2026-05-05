package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRequestDto(
        @NotNull(message = "El ID de la venta es obligatorio")
        Long venta_id,

        @NotNull(message = "El ID del producto es obligatorio")
        Long producto_id,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad mínima es 1")
        Integer cantidad
) {
}