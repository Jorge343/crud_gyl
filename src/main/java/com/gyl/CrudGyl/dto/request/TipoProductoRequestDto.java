package com.gyl.CrudGyl.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TipoProductoRequestDto (
        @NotBlank(message = "El nombre no puede ser vacio")
        String nombre,

        @NotBlank(message = "La descripcion no puede estar vacia")
        String descripcion){
}
