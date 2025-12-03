package com.prueba.service;


import com.prueba.dtos.TicketRequestDto;
import com.prueba.dtos.TicketResponseDto;
import com.prueba.model.Status;
import com.prueba.model.Ticket;
import com.prueba.model.Usuario;
import com.prueba.repository.TicketRepository;
import com.prueba.repository.UsuarioRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {


    public final TicketRepository ticketRepository;
    public final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Caching(evict = {
            @CacheEvict(value = "ticketPorId", key = "#result.id", condition = "#result != null"),
            @CacheEvict(value = "ticketsFiltrados", allEntries = true)
    })
    public TicketResponseDto crearTicket(TicketRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + dto.getUsuarioId() + " no existe"));

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setStatus(Status.valueOf(dto.getStatus()));
        ticket.setUsuario(usuario);

        Ticket saved = ticketRepository.save(ticket);

        // Limpiar la caché del usuario
        limpiarCacheUsuario(usuario.getId());

        return convertirATicketResponseDTO(saved);
    }

    @Caching(evict = {
            @CacheEvict(value = "ticketPorId", key = "#id"),
            @CacheEvict(value = "ticketsFiltrados", allEntries = true)
    })
    public TicketResponseDto actualizarTicket(UUID id, TicketRequestDto dto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        ticket.setDescripcion(dto.getDescripcion());
        ticket.setStatus(Status.valueOf(dto.getStatus()));

        Ticket actualizado = ticketRepository.save(ticket);
        return convertirATicketResponseDTO(actualizado);
    }

    @Caching(evict = {
            @CacheEvict(value = "ticketPorId", key = "#id"),
            @CacheEvict(value = "ticketsFiltrados", allEntries = true)
    })
    public void eliminarTicket(UUID id) {
        ticketRepository.deleteById(id);
    }

    @Cacheable(value = "ticketPorId", key = "#id")
    public TicketResponseDto obtenerPorId(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        return convertirATicketResponseDTO(ticket);
    }

    @Cacheable(value = "ticketsFiltrados", key = "{#status, #usuarioId, #pageable.pageNumber}")
    public Page<Ticket> obtenerTicketsFiltrados(Status status, UUID usuarioId, Pageable pageable) {
        Specification<Ticket> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        if (usuarioId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("usuario").get("id"), usuarioId));
        }

        return ticketRepository.findAll(spec, pageable);
    }

    private TicketResponseDto convertirATicketResponseDTO(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setDescripcion(ticket.getDescripcion());
        dto.setStatus(ticket.getStatus().name());
        dto.setFechaCreacion(ticket.getFechaCreacion());
        dto.setFechaActualizacion(ticket.getFechaActualizacion());
        dto.setUsuarioId(ticket.getUsuario().getId());
        dto.setUsuarioNombre(ticket.getUsuario().getNombres() + " " + ticket.getUsuario().getApellidos());
        return dto;
    }


    @CacheEvict(value = "tickets", key = "#usuarioId")
    public void limpiarCacheUsuario(UUID usuarioId) {
    }

}
