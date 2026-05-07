package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.request.ClienteRequestDto;
import com.gyl.CrudGyl.dto.response.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long id);

    ClienteResponseDto actualizar(Long id, ClienteRequestDto dto);

    ClienteResponseDto eliminar(Long id);

    List<ClienteResponseDto> busquedaApellido(String apellido);

    List<ClienteResponseDto> busquedaVigente(Boolean vigente);

    List<ClienteResponseDto> busquedaNombre(String nombre);
}