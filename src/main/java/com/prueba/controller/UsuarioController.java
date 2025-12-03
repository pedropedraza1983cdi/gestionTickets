package com.prueba.controller;


import com.prueba.dtos.UsuarioRequestDto;
import com.prueba.dtos.UsuarioResponseDto;
import com.prueba.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioRequestDto dto) {
        return ResponseEntity.ok(usuarioService.crearUsuario(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> actualizarUsuario(@PathVariable UUID id,
                                                                @Valid @RequestBody UsuarioRequestDto dto) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }
}

