package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.Sala;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalaDTO(
        @NotNull Long id,
        @NotBlank String nome,
        @NotNull Integer capacidade,
        @NotNull boolean salaInativa
) {
    public SalaDTO(Sala sala){
        this(
                sala.getId(),
                sala.getNome(),
                sala.getCapacidade(),
                sala.isSalaInativa()
        );
    }
}
