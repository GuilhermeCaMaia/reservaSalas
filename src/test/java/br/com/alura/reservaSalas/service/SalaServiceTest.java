package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarSalaDTO;
import br.com.alura.reservaSalas.dto.CadastrarSalaDTO;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalaServiceTest {
    @InjectMocks
    private SalaService salaService;

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private CadastrarSalaDTO cadastrarSalaDTO;
    @Mock
    private AtualizarSalaDTO atualizarSalaDTO;

    @Mock
    private Sala sala;

    @Test
    void deveriaCadastrarUmaSala(){
        CadastrarSalaDTO dto = new CadastrarSalaDTO(
                "30-a",40,false);

        given(salaRepository.existsByNome(dto.nome()))
                .willReturn(false);

        salaService.cadastarSala(dto);

        then(salaRepository).should().save(any(Sala.class));
    }

    @Test
    void naoDeveriaCadastrarUmaSala(){
        CadastrarSalaDTO dto = new CadastrarSalaDTO(
                "30-a",40,false);

        given(salaRepository.existsByNome(dto.nome()))
                .willReturn(true);

        Assertions.assertThrows(ValidacaoExcepetion.class, ()-> salaService.cadastarSala(dto));

        then(salaRepository).should(never()).save(any(Sala.class));
    }

    @Test
    void deveRetornarUmaListaDeSalas(){
        salaService.listarSalas();
        then(salaRepository).should().findAll();
    }

    @Test
    void deveRetornarUmaSala(){
        given(salaRepository.findById(sala.getId()))
                .willReturn(Optional.of(sala));
        salaService.buscarSalaPorId(sala.getId());
        then(salaRepository).should().findById(sala.getId());
    }

    @Test
    void deveriaAtualizarUmaSala(){
        AtualizarSalaDTO dto = new AtualizarSalaDTO(
                1L,"30-a",40,false);

        given(salaRepository.existsByNome(dto.nome()))
                .willReturn(false);
        given(salaRepository.getReferenceById(dto.id())).willReturn(sala);

        salaService.atualizarSala(dto);
        then(salaRepository).should().getReferenceById(dto.id());
        then(sala).should().atualizarDados(dto);
    }

    @Test
    void naoDeveriaAtualizarUmaSala(){
        AtualizarSalaDTO dto = new AtualizarSalaDTO(
                1L,"30-a",40,false);


        Assertions.assertThrows(ValidacaoExcepetion.class, () -> salaService.atualizarSala(dto));
    }

    @Test
    void deveriaDesativarUmaSala(){
        given(salaRepository.getReferenceById(sala.getId())).willReturn(sala);
        salaService.desativarSala(sala.getId());
        then(salaRepository).should().getReferenceById(sala.getId());
        then(sala).should().desativarSala();
    }

    @Test
    void deveriaAtivarUmaSala(){
        given(salaRepository.getReferenceById(sala.getId())).willReturn(sala);
        salaService.ativarSala(sala.getId());
        then(salaRepository).should().getReferenceById(sala.getId());
        then(sala).should().ativarSala();
    }

    @Test
    //@DisplayName()
    void deveriaExcluirUmaSala(){
        // Arrange
        Sala sala = new Sala();
        sala.setId(1L);
        when(salaRepository.findById(sala.getId())).thenReturn(Optional.of(sala));
        // Act
        salaService.excluirSala(sala.getId());
        // Assert
        verify(salaRepository).delete(sala);
    }

    @Test
    void naoDeveriaExcluirUmaSala(){
        // Arrange
        when(salaRepository.findById(1L)).thenReturn(Optional.empty());
        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> salaService.excluirSala(1L));
        verify(salaRepository, never()).delete(any(Sala.class));
    }

}
