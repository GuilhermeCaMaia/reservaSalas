package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarUsuarioDTO;
import br.com.alura.reservaSalas.dto.CadastrarUsuarioDTO;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CadastrarUsuarioDTO cadastrarUsuarioDTO;
    @Mock
    private Usuario usuario;

    @Test
    void deveriaCadastrarUsuarioComSucesso() {
        // Arrange
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO(
                "Guilherme", "guilherme@gmail.com"
        );
        BDDMockito.given(usuarioRepository.existsByEmail(dto.email()))
                .willReturn(false);
        // Act
        usuarioService.cadastrarUsuario(dto);
        // Assert
        BDDMockito.then(usuarioRepository).should().save(any(Usuario.class));
    }

    @Test
    void naoDeveriaCadastrarUsuario(){
        // Arrange
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO(
                "Guilherme", "guilherme@gmail.com"
        );
        BDDMockito.given(usuarioRepository.existsByEmail(dto.email()))
                .willReturn(true);
        // Act
        // Assert
        Assertions.assertThrows(ValidacaoExcepetion.class, ()-> usuarioService.cadastrarUsuario(dto));
        BDDMockito.then(usuarioRepository).should(never()).save(any(Usuario.class));
    }

    @Test
    void deveriaRetornarUmaListaDeUsuarios(){
        usuarioService.listarUsuarios();
        BDDMockito.then(usuarioRepository).should().findAll();
    }

    @Test
    void deveriaRetornarUmUsuario(){
        // Arrange
        BDDMockito.given(usuarioRepository.findById(usuario.getId()))
                .willReturn(Optional.of(usuario));
        // Act
        usuarioService.buscarSalaPorId(usuario.getId());
        // Assert
        BDDMockito.then(usuarioRepository).should().findById(usuario.getId());
    }

    @Test
    void deveriaAtualizarUsuario(){
        // Arrange
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO(
                1L, "Guilherme", "guilherme@gmail.com"
        );
        BDDMockito.given(usuarioRepository.getReferenceById(dto.id()))
                .willReturn(usuario);
        // Act
        usuarioService.atualizarUsuario(dto);
        // Assert
        BDDMockito.then(usuarioRepository).should().getReferenceById(dto.id());
        BDDMockito.then(usuario).should().atualizarDados(dto);
    }

    @Test
    void naoDeveriaAtualizarUsuario(){
        // Arrange
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO(
                1L, "Guilherme", "guilherme@gmail.com"
        );
        BDDMockito.given(usuarioRepository.getReferenceById(dto.id()))
                .willReturn(usuario);

        // Act
        // Assert
        Assertions.assertThrows(ValidacaoExcepetion.class, ()-> usuarioService.atualizarUsuario(dto));
    }

    @Test
    void deveriaExcluirUmUsuario(){
        // Arrange
        Sala sala = new Sala();
        sala.setId(1L);
        BDDMockito.when(usuarioRepository.findById(usuario.getId()))
                .thenReturn(Optional.of(usuario));
        // Act
        usuarioService.excluirUsuario(usuario.getId());
        // Assert
        BDDMockito.verify(usuarioRepository).delete(usuario);
    }

    @Test
    void naoDeveriaExcluirUmUsuario(){
        // Arrange
        BDDMockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        // Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> usuarioService.excluirUsuario(1L));
        BDDMockito.verify(usuarioRepository, never()).delete(any(Usuario.class));
    }
}