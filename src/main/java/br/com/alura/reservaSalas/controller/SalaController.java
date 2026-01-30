package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.repository.SalaRepository;
import br.com.alura.reservaSalas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/sala")
public class SalaController {
    @Autowired
    private SalaService salaService;

    // Criar
    @PostMapping("/sala")
    public ResponseEntity<String> cadastarSala(@RequestBody Sala sala){
        salaService.cadastarSala(sala);
        return ResponseEntity.ok().build();
    }
    // listar
    @GetMapping("/sala")
    public ResponseEntity<List<Sala>> listarSalas(){
        List<Sala> salas = salaService.listarSalas();
        return ResponseEntity.ok().body(salas);
    }
    @GetMapping("/sala/{id}")
    public ResponseEntity<Sala> listarSalaPorId(@PathVariable Long id){
        var dados = salaService.buscarSalaPorId(id);
        return ResponseEntity.ok().body(dados);
    }
    // Atualizar
    @PutMapping("/sala/{id}")
    public ResponseEntity<String> atualizarSala(@RequestBody Sala sala, @PathVariable Long id){
        salaService.atualizarSala(id, sala);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/sala/{id}/desativar")
    public ResponseEntity<String> desativarSala(@PathVariable Long id, @RequestBody Sala sala){
        salaService.desativarSala(id, sala);
        return ResponseEntity.ok().build();
    }
    // Deletar
    @DeleteMapping("/sala/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id){
        salaService.ExcluirSala(id);
        return ResponseEntity.noContent().build();
    }
}
