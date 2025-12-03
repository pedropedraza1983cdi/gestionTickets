package com.prueba.controller;


import com.prueba.dtos.TicketRequestDto;
import com.prueba.dtos.TicketResponseDto;
import com.prueba.model.Status;
import com.prueba.model.Ticket;
import com.prueba.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }



    @PostMapping
    public ResponseEntity<TicketResponseDto> crearTicket(@Valid @RequestBody TicketRequestDto dto) {
        return ResponseEntity.ok(ticketService.crearTicket(dto));
    }




    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> actualizarTicket(
            @PathVariable UUID id,
            @Valid @RequestBody TicketRequestDto dto) {
        return ResponseEntity.ok(ticketService.actualizarTicket(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable UUID id) {
        ticketService.eliminarTicket(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.obtenerPorId(id));
    }


    @GetMapping
    public ResponseEntity<Page<Ticket>> obtenerTicketsFiltrados(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) UUID usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Ticket> tickets = ticketService.obtenerTicketsFiltrados(status, usuarioId, PageRequest.of(page, size));
        return ResponseEntity.ok(tickets);
    }
}

