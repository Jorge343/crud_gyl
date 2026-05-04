package com.gyl.CrudGyl.dto;

import java.time.LocalDateTime;

public record VentaResponseDto(
        Long venta_id,
        LocalDateTime fecha_venta,
        Double total,
        String nombreCliente,
        String apellidoCliente
) {
}