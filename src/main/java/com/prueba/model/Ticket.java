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
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 500)

    @NotBlank(message = "El campo 'descripcion' es obligatorio")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private Status status;

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
