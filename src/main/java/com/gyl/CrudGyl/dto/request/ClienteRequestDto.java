package com.gyl.CrudGyl.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre es demasiado largo")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 100, message = "El apellido es demasiado largo")
        String apellido,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El formato del correo no es válido")
        String correo,

        @Pattern(regexp = "^\\d{8}$", message = "Numero de telefono invalido")
        String telefono,

        @Size(max = 100, message = "La dirección es demasiado larga")
        String direccion
) {

}