package com.prueba.service;


import com.prueba.dtos.UsuarioRequestDto;
import com.prueba.dtos.UsuarioResponseDto;
import com.prueba.model.Usuario;
import com.prueba.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDto crearUsuario(UsuarioRequestDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombres(dto.getNombres());
        usuario.setApellidos(dto.getApellidos());

        Usuario saved = usuarioRepository.save(usuario);
        return convertirAResponseDTO(saved);
    }

    public UsuarioResponseDto actualizarUsuario(UUID id, UsuarioRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombres(dto.getNombres());
        usuario.setApellidos(dto.getApellidos());

        Usuario actualizado = usuarioRepository.save(usuario);
        return convertirAResponseDTO(actualizado);
    }

    public List<UsuarioResponseDto> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDto obtenerPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertirAResponseDTO(usuario);
    }

    private UsuarioResponseDto convertirAResponseDTO(Usuario usuario) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(usuario.getId());
        dto.setNombres(usuario.getNombres());
        dto.setApellidos(usuario.getApellidos());
        dto.setFechaCreacion(usuario.getFechaCreacion());
        dto.setFechaActualizacion(usuario.getFechaActualizacion());
        return dto;
    }

}

