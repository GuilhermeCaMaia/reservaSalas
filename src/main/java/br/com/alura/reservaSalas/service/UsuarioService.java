package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarUsuarioDTO;
import br.com.alura.reservaSalas.dto.CadastrarUsuarioDTO;
import br.com.alura.reservaSalas.dto.UsuarioDTO;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServece {
    @Autowired
    private UsuarioRepository usuarioRepository;
    // Criar
    public void cadastrarUsuario(CadastrarUsuarioDTO dto) {
        boolean dadosCadastrados = usuarioRepository.findByEmail(dto.email());
        if (dadosCadastrados) {
            throw new ValidacaoExcepetion("Dados ja cadastrados");
        }
        usuarioRepository.save(new Usuario(dto));
    }
    // Listar
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::new).toList();
    }
    public UsuarioDTO buscarSalaPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
    // Atualizar
    @Transactional
    public void atualizarUsuario(AtualizarUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.id());
        boolean dadosCadastrados = usuarioRepository.findByEmail(dto.email());
        if (dadosCadastrados) {
            throw new ValidacaoExcepetion("Dados ja cadastrados");
        }
        usuario.atualizarUsuario(dto);
    }
    // Deletar
    public void ExcluirUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
        //        usuarioRepository.deleteById(id);
    }
}







