package com.gyl.CrudGyl.dto.response;

public record DetalleVentaResponseDto(
        Long idDetalleVenta,
        Long idVenta,
        String nombreProducto,
        Integer cantidad,
        Double precioUnitario,
        Double subtotal,
        Boolean vigente
) {
}
