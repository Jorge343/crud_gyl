package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.dto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoService {

    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long id);

    TipoProductoResponseDto actualizar(Long id, TipoProductoRequestDto dto);

    void eliminar(Long id);

    List<TipoProductoResponseDto> busquedaNombre(String nombre);

    List<TipoProductoResponseDto> busquedaNoVigente(Boolean vigente);

}
