package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.response.ClienteResponseDto;
import com.gyl.CrudGyl.dto.request.ClienteRequestDto;
import com.gyl.CrudGyl.entity.Cliente;

public class ClienteMapper {

    private ClienteMapper() {

    }

    public static Cliente toEntity(ClienteRequestDto dto){
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setCorreo(dto.correo());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
        cliente.setVigente(true);

        return cliente;
    }

    public static ClienteResponseDto toResponseDto(Cliente cliente){
        return new ClienteResponseDto(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getVigente()
        );
    }

    public static void updateEntity(Cliente cliente, ClienteRequestDto dto){
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setCorreo(dto.correo());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
    }
}