package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.service.UsuarioServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServece usuarioServece;

    // Criar
    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioServece.cadastrarUsuario(usuario);
        return ResponseEntity.ok().build();
    }
    // Listar
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioServece.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUsuarioPorId(@PathVariable Long id){
        var dados = usuarioServece.buscarSalaPorId(id);
        return ResponseEntity.ok().body(dados);
    }
    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        usuarioServece.atualizarUsuario(id, usuario);
        return ResponseEntity.ok().build();
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioServece.ExcluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}










