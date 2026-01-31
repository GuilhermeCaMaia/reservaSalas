package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.dto.AtualizarUsuarioDTO;
import br.com.alura.reservaSalas.dto.CadastrarUsuarioDTO;
import br.com.alura.reservaSalas.dto.UsuarioDTO;
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
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastrarUsuarioDTO dto) {
        usuarioServece.cadastrarUsuario(dto);
        return ResponseEntity.ok().build();
    }
    // Listar
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        List<UsuarioDTO> usuarios = usuarioServece.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listarUsuarioPorId(@PathVariable Long id){
        UsuarioDTO dto = usuarioServece.buscarSalaPorId(id);
        return ResponseEntity.ok().body(dto);
    }
    // Atualizar
    @PutMapping
    public ResponseEntity<String> atualizarUsuario(@RequestBody AtualizarUsuarioDTO dto){
        usuarioServece.atualizarUsuario(dto);
        return ResponseEntity.ok().build();
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioServece.ExcluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}










