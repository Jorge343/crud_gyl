package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long id);

    ClienteResponseDto actualizar(Long id, ClienteRequestDto dto);

    void eliminar(Long id);

    List<ClienteResponseDto> busquedaApellido(String apellido);

    List<ClienteResponseDto> busquedaNoVigente(Boolean vigente);

    List<ClienteResponseDto> busquedaNombre(String nombre);
}