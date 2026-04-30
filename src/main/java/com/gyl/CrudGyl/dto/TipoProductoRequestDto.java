package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TipoProductoRequestDto (
        @NotBlank(message = "El nombre no puede ser vacio")
        String nombre,

        @NotBlank(message = "La descripcion no puede estar vacia")
        String descripcion){
}
