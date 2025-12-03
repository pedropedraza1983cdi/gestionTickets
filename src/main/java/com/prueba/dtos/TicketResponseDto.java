package com.prueba.dtos;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketResponseDto {


    private UUID id;
    private String descripcion;
    private String status;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private UUID usuarioId;
    private String usuarioNombre;

}
