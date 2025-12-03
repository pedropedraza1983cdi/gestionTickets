package com.prueba.service;

import com.prueba.model.Usuario;
import com.prueba.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.prueba.dtos.UsuarioRequestDto;
import com.prueba.dtos.UsuarioResponseDto;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario_debeGuardarUsuario() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setNombres("Mario");
        dto.setApellidos("Lopez");

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNombres("Mario");
        usuario.setApellidos("Lopez");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        var response = usuarioService.crearUsuario(dto);

        assertNotNull(response.getId());
        assertEquals("Mario", response.getNombres());
    }

    @Test
    void actualizarUsuario_usuarioNoExiste_debeLanzarExcepcion() {
        UUID id = UUID.randomUUID();
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setNombres("Nuevo");
        dto.setApellidos("Nombre");

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.actualizarUsuario(id, dto));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }
}

