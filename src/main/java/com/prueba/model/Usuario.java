package com.prueba.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;


    @NotBlank(message = "El campo 'nombres' es obligatorio")
    private String nombres;


    @NotBlank(message = "El campo 'apellidos' es obligatorio")
    private String apellidos;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = fechaCreacion;
    }

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
