package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.request.ClienteRequestDto;
import com.gyl.CrudGyl.dto.response.ClienteResponseDto;
import com.gyl.CrudGyl.entity.Cliente;
import com.gyl.CrudGyl.exception.RecursoUnicoYaExistenteException;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ClienteMapper;
import com.gyl.CrudGyl.repository.ClienteRepository;
import com.gyl.CrudGyl.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDto crear(ClienteRequestDto dto) {

        if (!(clienteRepository.findByCorreo(dto.correo()).isEmpty()))
            throw new RecursoUnicoYaExistenteException("El email ingresado ya existe");

        if (!(clienteRepository.findByTelefono(dto.telefono()).isEmpty()))
            throw new RecursoUnicoYaExistenteException("El telefono ingresado ya existe");

        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toResponseDto(guardado);
    }

    @Override
    public List<ClienteResponseDto> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }

    @Override
    public ClienteResponseDto buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el Cliente con ID " + id
                ));
    }

    @Override
    public ClienteResponseDto actualizar(Long id, ClienteRequestDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el Cliente con ID " + id
                ));

        ClienteMapper.updateEntity(cliente, dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toResponseDto(guardado);
    }

    @Override
    public ClienteResponseDto eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el Cliente con ID " + id
                ));

        cliente.setVigente(false);
        clienteRepository.save(cliente);
        return ClienteMapper.toResponseDto(cliente);
    }

    @Override
    public List<ClienteResponseDto> busquedaApellido(String apellido) {
        return clienteRepository.findByApellido(apellido)
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<ClienteResponseDto> busquedaVigente(Boolean vigente) {
        return clienteRepository.findByVigente(vigente)
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<ClienteResponseDto> busquedaNombre(String nombre) {
        return clienteRepository.findByNombre(nombre)
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }
}