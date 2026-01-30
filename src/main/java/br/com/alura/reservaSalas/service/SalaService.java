package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    // Criar
    public void cadastarSala(Sala sala){
        salaRepository.save(sala);
    }
    // listar
    public List<Sala> listarSalas(){
        return salaRepository.findAll();
    }
    public Sala buscarSalaPorId(Long id){
        Optional<Sala> sala = salaRepository.findById(id);
        return sala.orElse(null);
    }
    // Atualizar
    public void atualizarSala(Long id, Sala dados){
        Sala sala = salaRepository.getReferenceById(id);
        sala.setNumero(dados.getNumero());
        sala.setCapacidade(dados.getCapacidade());
        sala.setSalaInativa(dados.isSalaInativa());
    }
    public void desativarSala(Long id, Sala dados) {
        Sala sala = salaRepository.getReferenceById(id);
        sala.setSalaInativa(true);
    }
    // Deletar
    public void ExcluirSala(Long id){
        Sala sala = salaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada"));
        salaRepository.delete(sala);
    }

}
