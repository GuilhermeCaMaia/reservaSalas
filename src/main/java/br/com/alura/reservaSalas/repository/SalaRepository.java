package br.com.alura.reservaSalas.repository;

import br.com.alura.reservaSalas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

}
