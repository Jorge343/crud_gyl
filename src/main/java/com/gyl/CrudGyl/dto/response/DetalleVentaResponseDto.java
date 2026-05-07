package com.gyl.CrudGyl.dto.response;

public record DetalleVentaResponseDto(
        Long idDetalleVenta,
        String nombreProducto,
        Integer cantidad,
        Double precioUnitario,
        Double subtotal,
        Boolean vigente
) {
}
