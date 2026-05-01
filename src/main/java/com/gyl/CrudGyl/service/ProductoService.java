package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.TipoProductoResponseDto;

import java.util.List;

public interface ProductoService {

   ProductoResponseDto crear(ProductoRequestDto dto);

   List<ProductoResponseDto> listar();

   ProductoResponseDto buscarPorId(Long id);

   ProductoResponseDto actualizar(Long id, ProductoRequestDto dto);

   void eliminar(Long id);

   List<ProductoResponseDto> busquedaNombre(String nombre);

   List<ProductoResponseDto> busquedaNoVigente(Boolean vigente);
}
