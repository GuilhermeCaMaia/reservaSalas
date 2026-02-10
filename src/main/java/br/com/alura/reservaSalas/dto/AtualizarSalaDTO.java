package br.com.alura.reservaSalas.dto;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record AtualizarSalaDTO(
        @NotNull Long id,
        @NotBlank (message = "o nome não pode estar em branco")
        String nome,
        @NotNull Integer capacidade,
        @NotNull boolean salaInativa
) {
}
