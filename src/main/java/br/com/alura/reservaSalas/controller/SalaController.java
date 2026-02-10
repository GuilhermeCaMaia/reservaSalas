package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.dto.*;
import br.com.alura.reservaSalas.excepetion.ValidacaoExcepetion;
import br.com.alura.reservaSalas.service.SalaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sala")
public class SalaController {
    @Autowired
    private SalaService salaService;

    // Criar
    @PostMapping
    public ResponseEntity<String> cadastarSala(@RequestBody @Valid CadastrarSalaDTO dto){
        try {
            salaService.cadastarSala(dto);
            return ResponseEntity.ok().build();
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // listar
    @GetMapping
    public ResponseEntity<List<SalaDTO>> listarSalas(){
        List<SalaDTO> salas = salaService.listarSalas();
        return ResponseEntity.ok().body(salas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> listarSalaPorId(@PathVariable Long id){
        try {
            SalaDTO dto = salaService.buscarSalaPorId(id);
            return ResponseEntity.ok().body(dto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    // Atualizar
    @PutMapping
    public ResponseEntity<String> atualizarSala(@RequestBody @Valid AtualizarSalaDTO dto){
        try {
            salaService.atualizarSala(dto);
            return ResponseEntity.ok().build();
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}/desativar")
    public ResponseEntity<String> desativarSala(@PathVariable Long id){
        try {
            salaService.desativarSala(id);
            return ResponseEntity.ok().build();
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}/ativar")
    public ResponseEntity<String> ativarSala(@PathVariable Long id){
        try {
            salaService.ativarSala(id);
            return ResponseEntity.ok().build();
        }catch (ValidacaoExcepetion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id){
        try {
            salaService.excluirSala(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
