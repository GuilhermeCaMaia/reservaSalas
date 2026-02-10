package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarReservaDTO;
import br.com.alura.reservaSalas.dto.CriarReservaDTO;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.model.Reserva;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.model.StatusReserva;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.ReservaRepository;
import br.com.alura.reservaSalas.repository.SalaRepository;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private SalaRepository salaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Reserva reserva;
    @Mock
    private Sala sala;
    @Mock
    private Usuario usuario;

    @Mock
    private CriarReservaDTO criarReservaDTO;

    @Test
    void deveriaCriarReserva(){
        CriarReservaDTO dto = new CriarReservaDTO(
                1L, 1L, LocalDate.parse("2025-01-27"),
                LocalDate.parse("2025-02-27"), StatusReserva.ATIVO);

        given(salaRepository.findById(dto.idSala()))
                .willReturn(Optional.of(sala));
        given(usuarioRepository.findById(dto.idUsuario()))
                .willReturn(Optional.of(usuario));

        reservaService.criarReserva(dto);

        then(reservaRepository).should().save(any(Reserva.class));
    }

    @Test
    void naoDeveriaCriarUmaReservaSalaNaoEncontrada(){
        CriarReservaDTO dto = new CriarReservaDTO(
                1L, 1L, LocalDate.parse("2025-01-27"),
                LocalDate.parse("2025-02-27"), StatusReserva.ATIVO);

        given(salaRepository.findById(dto.idSala()))
                .willReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()-> reservaService.criarReserva(dto));
        then(reservaRepository).should(never()).save(any(Reserva.class));
    }

    @Test
    void naoDeveriaCriarUmaReservaUsuarioNaoEncontrada(){
        CriarReservaDTO dto = new CriarReservaDTO(
                1L, 1L, LocalDate.parse("2025-01-27"),
                LocalDate.parse("2025-02-27"), StatusReserva.ATIVO);

        given(salaRepository.findById(dto.idSala()))
                .willReturn(Optional.of(sala));
        given(usuarioRepository.findById(dto.idUsuario()))
                .willReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()-> reservaService.criarReserva(dto));
        then(reservaRepository).should(never()).save(any(Reserva.class));
    }

    @Test
    void deveriaListarReservas(){
        reservaService.listarReservas();
        then(reservaRepository).should().findAll();
    }

    @Test
    void deveriaBuscarReservaPorId(){
        given(reservaRepository.findById(reserva.getId()))
                .willReturn(Optional.of(reserva));
        reservaService.buscarReservaPorId(reserva.getId());

        then(reservaRepository).should().findById(reserva.getId());
    }

    @Test
    void deveriaAtualizarUmaReserva(){
        AtualizarReservaDTO dto = new AtualizarReservaDTO(
                1L, LocalDate.parse("2025-01-27"),
                LocalDate.parse("2025-01-27"), StatusReserva.ATIVO
        );
        given(reservaRepository.getReferenceById(dto.id()))
                .willReturn(reserva);
        reservaService.atualizarReserva(dto);
        then(reservaRepository).should().getReferenceById(dto.id());
        then(reserva).should().atualizarReserva(dto);
    }

    @Test
    void deveriaCancelarUmaReserva(){
        given(reservaRepository.getReferenceById(reserva.getId())).willReturn(reserva);
        reservaService.cancelarReserva(reserva.getId());

        then(reservaRepository).should().getReferenceById(reserva.getId());
        then(reserva).should().cancelarReserva();
    }

    @Test
    void deveriaExcluirUmaReserva(){
        // ARRANGE
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));
        // ACT
        reservaService.excluirReserva(reserva.getId());
        // ASSERT
        verify(reservaRepository).delete(reserva);
    }

    @Test
    void naoDeveriaExcluirUmaReserva(){
        // ARRANGE
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.empty());
        // ACT
        // ASSERT
        assertThrows(EntityNotFoundException.class,
                ()-> reservaService.excluirReserva(reserva.getId()));
        verify(reservaRepository, never()).delete(any(Reserva.class));
    }
}












