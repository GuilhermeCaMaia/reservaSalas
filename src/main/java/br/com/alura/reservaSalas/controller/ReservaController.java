package br.com.alura.reservaSalas.controller;

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


    // Criar
    @PostMapping("/{idSala}/{emailUsuario}")
    public ResponseEntity<String> ReservarSala(@PathVariable Long idSala,
                                               @PathVariable String emailUsuario,
                                               @RequestBody Reserva reserva){
//        Sala sala = salaService.buscarSalaPorId(idSala);
//        Usuario usuario = usuarioServece.carregarUsuario(emailUsuario);
        reservaService.criarReserva(reserva, idSala, emailUsuario);
        return ResponseEntity.ok("Reserva realizada com sucesso");
    }
    // Listar
    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas(){
        List<Reserva> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable Long id){
        var dados = reservaService.buscarReservaPorId(id);
        return ResponseEntity.ok(dados);
    }
    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva){
        reservaService.atualizarReserva(id, reserva);
        return ResponseEntity.ok("Reserva atualizada com sucesso");
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReservaPorId(@PathVariable Long id){
        reservaService.ExcluirReserva(id);
        return ResponseEntity.noContent().build();
    }
}













