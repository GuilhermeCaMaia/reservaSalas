package br.com.alura.reservaSalas.service;

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
    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    // Listar
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    public Usuario buscarSalaPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }
//    public Usuario carregarUsuario(String emailUsuario) {
//        Optional<Usuario> optional = usuarioRepository.findByEmail(emailUsuario);
//        return optional.orElseThrow(null);
//    }
    // Atualizar
    public void atualizarUsuario(Long id, Usuario dados) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuarioRepository.save(usuario);
    }
    // Deletar
    public void ExcluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}







