package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.StatusReserva;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

public record CriarReservarDTO(
        @NotNull Long idSala,
        @NotNull Long idUsuario,
        @NotNull LocalDate dataInicio,
        @NotNull LocalDate dataFim,
        @NotNull StatusReserva status
        ) {
}
