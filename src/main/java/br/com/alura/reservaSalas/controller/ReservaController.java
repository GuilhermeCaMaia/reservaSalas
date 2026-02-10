package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.dto.AtualizarReservaDTO;
import br.com.alura.reservaSalas.dto.CriarReservaDTO;
import br.com.alura.reservaSalas.dto.ReservaDTO;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reserva")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    // Criar
    @PostMapping
    public ResponseEntity<String> ReservarSala(@RequestBody @Valid CriarReservaDTO dto){
        try {
            reservaService.criarReserva(dto);
            return ResponseEntity.ok("Reserva realizada com sucesso");
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Listar
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas(){
        List<ReservaDTO> reservas = reservaService.listarReservas();
        return ResponseEntity.ok().body(reservas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId(@PathVariable Long id){
        try {
            ReservaDTO dto = reservaService.buscarReservaPorId(id);
            return ResponseEntity.ok().body(dto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    // Atualizar
    @PutMapping
    public ResponseEntity<String> atualizarReserva(@RequestBody @Valid AtualizarReservaDTO dto){
        try {
            reservaService.atualizarReserva(dto);
            return ResponseEntity.ok("Reserva atualizada com sucesso");
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarReserva(@PathVariable Long id){
        try {
            reservaService.cancelarReserva(id);
            return ResponseEntity.ok("Reserva Cancelada com sucesso");
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReservaPorId(@PathVariable Long id){
        try {
            reservaService.excluirReserva(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}













