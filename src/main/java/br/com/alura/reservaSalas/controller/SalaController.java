package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.dto.*;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.repository.SalaRepository;
import br.com.alura.reservaSalas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
public class SalaController {
    @Autowired
    private SalaService salaService;

    // Criar
    @PostMapping
    public ResponseEntity<String> cadastarSala(@RequestBody CadastrarSalaDTO dto){
        salaService.cadastarSala(dto);
        return ResponseEntity.ok().build();
    }
    // listar
    @GetMapping
    public ResponseEntity<List<SalaDTO>> listarSalas(){
        List<SalaDTO> salas = salaService.listarSalas();
        return ResponseEntity.ok().body(salas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> listarSalaPorId(@PathVariable Long id){
        SalaDTO dto = salaService.buscarSalaPorId(id);
        return ResponseEntity.ok().body(dto);
    }
    // Atualizar
    @PutMapping
    public ResponseEntity<String> atualizarSala(@RequestBody AtualizarSalaDTO dto){
        salaService.atualizarSala(dto);
        return ResponseEntity.ok().build();
    }
    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id){
        salaService.ExcluirSala(id);
        return ResponseEntity.noContent().build();
    }
}
