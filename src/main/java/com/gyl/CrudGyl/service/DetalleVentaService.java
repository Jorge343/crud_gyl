package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.request.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.response.DetalleVentaResponseDto;

import java.util.List;


public interface DetalleVentaService {

    DetalleVentaResponseDto agregarProducto(DetalleVentaRequestDto dto);

    List<DetalleVentaResponseDto> listarPorVenta(Long idVenta);

    List<DetalleVentaResponseDto> busquedaVigente(Boolean vigente);

    void eliminar(Long id);
}