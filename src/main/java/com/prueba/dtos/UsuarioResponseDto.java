package com.prueba.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UsuarioResponseDto {


    private UUID id;
    private String nombres;
    private String apellidos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

}
