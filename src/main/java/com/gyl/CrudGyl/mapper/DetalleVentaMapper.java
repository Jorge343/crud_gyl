package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.response.DetalleVentaResponseDto;
import com.gyl.CrudGyl.entity.DetalleVenta;

public class DetalleVentaMapper {

    private DetalleVentaMapper() {}

    public static DetalleVenta toEntity(DetalleVentaRequestDto dto) {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setCantidad(dto.cantidad());
        return detalle;
    }

    public static DetalleVentaResponseDto toResponseDto(DetalleVenta detalle) {
        return new DetalleVentaResponseDto(
                detalle.getDetalleVenta_id(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecio_unitario(),
                detalle.getSubtotal(),
                detalle.getVigente()
        );
    }
}
