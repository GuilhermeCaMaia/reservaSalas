package br.com.alura.reservaSalas.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record CadastrarSalaDTO(
        @NotNull String numero,
        @NotNull Integer capacidade,
        @NotNull boolean salaInativa
) {
}
