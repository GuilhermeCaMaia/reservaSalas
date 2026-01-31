package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarUsuarioDTO;
import br.com.alura.reservaSalas.dto.CadastrarUsuarioDTO;
import br.com.alura.reservaSalas.dto.UsuarioDTO;
import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServece {
    @Autowired
    private UsuarioRepository usuarioRepository;
    // Criar
    public void cadastrarUsuario(CadastrarUsuarioDTO dto) {
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
    public void atualizarUsuario(AtualizarUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.id());
        usuario.atualizarUsuario(dto);
    }
    // Deletar
    public void ExcluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}







