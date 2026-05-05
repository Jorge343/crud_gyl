package com.gyl.CrudGyl.dto;

public record TipoProductoResponseDto(
        Long tipo_id,
        String nombre,
        String descripcion,
        Boolean vigente
) {

}
