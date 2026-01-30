package br.com.alura.reservaSalas.repository;

import br.com.alura.reservaSalas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
