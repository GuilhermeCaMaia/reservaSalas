package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarReservaDTO;
import br.com.alura.reservaSalas.dto.CriarReservaDTO;
import br.com.alura.reservaSalas.dto.ReservaDTO;
import br.com.alura.reservaSalas.model.Reserva;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.ReservaRepository;
import br.com.alura.reservaSalas.repository.SalaRepository;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar
    public void criarReserva(CriarReservaDTO dto){
        Objects.requireNonNull(dto);
        Sala sala = salaRepository.findById(dto.idSala())
                .orElseThrow(()-> new RuntimeException("Sala não encontrada"));
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Reserva reserva = new Reserva(dto, sala, usuario);
        reservaRepository.save(reserva);
    }

    // Listar
    public List<ReservaDTO> listarReservas() {
        return reservaRepository.findAll().stream()
                .map(ReservaDTO::new).toList();
    }

    public ReservaDTO buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Reserva não encontrada"));
        return new ReservaDTO(
                reserva.getId(),
                reserva.getDataInicio(),
                reserva.getDataFim(),
                reserva.getStatus(),
                reserva.getSala(),
                reserva.getUsuario()
        );
    }
    // Atualizar
    @Transactional
    public void atualizarReserva(AtualizarReservaDTO dto) {
        Reserva reserva = reservaRepository.getReferenceById(dto.id());
        reserva.atualizarReserva(dto);
    }
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.getReferenceById(id);
        reserva.cancelarReserva();
    }

    // Deletar
    public void excluirReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada"));
        reservaRepository.delete(reserva);
    }


}
