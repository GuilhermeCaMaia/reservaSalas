package br.com.alura.reservaSalas.repository;

import br.com.alura.reservaSalas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    boolean existsByNome(String nome);
}
