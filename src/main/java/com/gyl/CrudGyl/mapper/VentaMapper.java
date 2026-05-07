package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.VentaRequestDto;
import com.gyl.CrudGyl.dto.response.VentaResponseDto;
import com.gyl.CrudGyl.entity.Venta;
import java.time.LocalDateTime;

public class VentaMapper {

    private VentaMapper() {}

    public static Venta toEntity(VentaRequestDto dto) {
        Venta venta = new Venta();
        venta.setFecha_venta(LocalDateTime.now());
        venta.setTotal(0.0);
        return venta;
    }

    public static VentaResponseDto toResponseDto(Venta venta) {
        return new VentaResponseDto(
                venta.getIdVenta(),
                venta.getFecha_venta(),
                venta.getTotal(),
                venta.getCliente().getNombre(),
                venta.getCliente().getApellido(),
                venta.getVigente()
        );
    }
}