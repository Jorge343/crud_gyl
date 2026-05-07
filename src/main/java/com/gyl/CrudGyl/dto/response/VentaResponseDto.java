package com.gyl.CrudGyl.dto.response;

import java.time.LocalDateTime;

public record VentaResponseDto(
        Long idVenta,
        LocalDateTime fecha_venta,
        Double total,
        String nombreCliente,
        String apellidoCliente,
        Boolean vigente
) {
}