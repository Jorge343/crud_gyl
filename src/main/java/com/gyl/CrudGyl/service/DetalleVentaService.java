package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.DetalleVentaResponseDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DetalleVentaService {

    DetalleVentaResponseDto agregarProducto(DetalleVentaRequestDto dto);

    List<DetalleVentaResponseDto> listarPorVenta(Long idVenta);

    List<DetalleVentaResponseDto> busquedaVigente(Boolean vigente);

    void eliminar(Long id);
}