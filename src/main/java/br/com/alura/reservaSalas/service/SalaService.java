package br.com.alura.reservaSalas.service;

import br.com.alura.reservaSalas.dto.AtualizarSalaDTO;
import br.com.alura.reservaSalas.dto.CadastrarSalaDTO;
import br.com.alura.reservaSalas.dto.SalaDTO;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    // Criar
    public void cadastarSala(CadastrarSalaDTO dto){
        salaRepository.save(new Sala(dto));
    }
    // listar
    public List<SalaDTO> listarSalas(){
        return salaRepository.findAll().stream().map(SalaDTO::new).toList();
    }
    public SalaDTO buscarSalaPorId(Long id){
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        return new SalaDTO(
                sala.getId(),
                sala.getNumero(),
                sala.getCapacidade(),
                sala.isSalaInativa()
        );
    }
    // Atualizar
    @Transactional
    public void atualizarSala(AtualizarSalaDTO dto){
        Sala sala = salaRepository.getReferenceById(dto.id());
        sala.atualizarDados(dto);
    }
    // Deletar
    public void ExcluirSala(Long id){
        Sala sala = salaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada"));
        salaRepository.delete(sala);
    }

}
