package com.gyl.CrudGyl.dto.response;

public record TipoProductoResponseDto(
        Long tipo_id,
        String nombre,
        String descripcion,
        Boolean vigente
) {

}
