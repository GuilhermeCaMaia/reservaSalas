package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.model.Reserva;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.ReservaRepository;
import br.com.alura.reservaSalas.repository.SalaRepository;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar
    public void criarReserva(Reserva reserva, Long idSala, String emailUsuario){
        Sala sala = salaRepository.findById(idSala)
                .orElseThrow(()-> new RuntimeException("Sala não encontrada"));
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        reserva.setSala(sala);
        reserva.setUsuario(usuario);
        reservaRepository.save(reserva);
    }

    // Listar
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Reserva não encontrada"));
        return reserva;
    }
    // Atualizar
    public void atualizarReserva(Long id, Reserva dados) {
        Reserva reserva = reservaRepository.getReferenceById(id);
        reserva.setDataInicio(dados.getDataInicio());
        reserva.setDataFim(dados.getDataFim());
        reserva.setStatus(dados.getStatus());
        reservaRepository.save(reserva);
    }

    // Deletar
    public void ExcluirReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada"));
        reservaRepository.delete(reserva);
    }


}
