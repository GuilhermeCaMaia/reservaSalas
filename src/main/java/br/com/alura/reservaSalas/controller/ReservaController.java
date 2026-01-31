package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.dto.CriarReservaDTO;
import br.com.alura.reservaSalas.dto.ReservaDTO;
import br.com.alura.reservaSalas.dto.SalaDTO;
import br.com.alura.reservaSalas.model.Reserva;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.SalaRepository;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import br.com.alura.reservaSalas.service.ReservaService;
import br.com.alura.reservaSalas.service.SalaService;
import br.com.alura.reservaSalas.service.UsuarioServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private SalaService salaService;
    @Autowired
    private UsuarioServece usuarioServece;

    // "/{idSala}/{emailUsuario}"
    // Criar
    @PostMapping
    public ResponseEntity<String> ReservarSala(@RequestBody CriarReservaDTO dto){
        reservaService.criarReserva(dto);
        return ResponseEntity.ok("Reserva realizada com sucesso");
    }
    // Listar
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas(){
        List<ReservaDTO> reservas = reservaService.listarReservas();
        return ResponseEntity.ok().body(reservas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId(@PathVariable Long id){
        ReservaDTO dto = reservaService.buscarReservaPorId(id);
        return ResponseEntity.ok().body(dto);
    }
    // Atualizar
    @PutMapping
    public ResponseEntity<String> atualizarReserva(@RequestBody ReservaDTO dto){
        reservaService.atualizarReserva(dto);
        return ResponseEntity.ok("Reserva atualizada com sucesso");
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReservaPorId(@PathVariable Long id){
        reservaService.ExcluirReserva(id);
        return ResponseEntity.noContent().build();
    }
}













