package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.StatusReserva;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarReservaDTO(
        @NotNull Long id,
        @NotNull (message = "Insira a data de inicio")
        LocalDate dataInicio,
        @NotNull (message = "Insira a data de encerramento")
        LocalDate dataFim,
        @NotNull StatusReserva status
        ) {
}
