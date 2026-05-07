package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoService {

    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long id);

    TipoProductoResponseDto actualizar(Long id, TipoProductoRequestDto dto);

    void eliminar(Long id);

    List<TipoProductoResponseDto> busquedaNombre(String nombre);

    List<TipoProductoResponseDto> busquedaVigente(Boolean vigente);

}
