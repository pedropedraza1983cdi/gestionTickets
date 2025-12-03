package com.prueba.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.dtos.UsuarioRequestDto;
import com.prueba.dtos.UsuarioResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Desactiva filtros de seguridad
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearUsuario_debeRetornar201() throws Exception {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setNombres("Juan");
        dto.setApellidos("Perez");


        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Perez"));

    }
}

