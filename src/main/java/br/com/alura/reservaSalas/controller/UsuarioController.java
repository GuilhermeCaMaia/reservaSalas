package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.dto.AtualizarUsuarioDTO;
import br.com.alura.reservaSalas.dto.CadastrarUsuarioDTO;
import br.com.alura.reservaSalas.dto.UsuarioDTO;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServece;

    // Criar
    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO dto) {
        try {
            usuarioServece.cadastrarUsuario(dto);
            return ResponseEntity.ok().build();
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Listar
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        List<UsuarioDTO> usuarios = usuarioServece.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listarUsuarioPorId(@PathVariable Long id){
        try {
            UsuarioDTO dto = usuarioServece.buscarSalaPorId(id);
            return ResponseEntity.ok().body(dto);
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.notFound().build();
        }
    }
    // Atualizar
    @PutMapping
    public ResponseEntity<String> atualizarUsuario(@RequestBody @Valid AtualizarUsuarioDTO dto){
        try {
            usuarioServece.atualizarUsuario(dto);
            return ResponseEntity.ok().build();
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        try {
            usuarioServece.excluirUsuario(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}










