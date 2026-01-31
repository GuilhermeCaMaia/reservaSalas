package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.Reserva;
import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.model.StatusReserva;
import br.com.alura.reservaSalas.model.Usuario;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

public record ReservaDTO(
        @NotNull Long id,
        @NotNull LocalDate dataInicio,
        @NotNull LocalDate dataFim,
        @NotNull StatusReserva status,
        @NotNull Sala sala,
        @NotNull Usuario usuario
        ) {
    public ReservaDTO(Reserva reserva){
        this(
                reserva.getId(),
                reserva.getDataInicio(),
                reserva.getDataFim(),
                reserva.getStatus(),
                reserva.getSala(),
                reserva.getUsuario()
        );
    }
}
