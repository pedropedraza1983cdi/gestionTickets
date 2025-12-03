package com.prueba.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class TicketRequestDto {

    @NotBlank(message = "El campo 'descripcion' es obligatorio")
    private String descripcion;

    @NotNull(message = "El campo 'usuarioId' es obligatorio")
    private UUID usuarioId;

    @NotNull(message = "El campo 'status' es obligatorio")
    private String status; // ABIERTO o CERRADO
}
