package com.prueba.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioRequestDto {


    @NotBlank(message = "El campo 'nombres' es obligatorio")
    private String nombres;

    @NotBlank(message = "El campo 'apellidos' es obligatorio")
    private String apellidos;

}

