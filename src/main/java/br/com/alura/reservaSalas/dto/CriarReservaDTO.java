package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.StatusReserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record CriarReservaDTO(
        @NotNull(message = "ID da sala é obrigatório")
        Long idSala,
        @NotNull(message = "ID do usuário é obrigatório")
        Long idUsuario,
        @NotNull(message = "A data de início é obrigatória")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataInicio,
        @NotNull(message = "A data de fim é obrigatória")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataFim,
        @NotNull(message = "O status é obrigatório")
        StatusReserva status
        ) {
}
