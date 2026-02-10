package br.com.alura.reservaSalas.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record AtualizarSalaInativaDTO(
        @NotNull Long id,
        @NotNull Boolean salaInativa) {
}
