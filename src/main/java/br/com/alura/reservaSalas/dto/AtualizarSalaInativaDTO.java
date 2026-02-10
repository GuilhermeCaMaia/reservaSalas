package br.com.alura.reservaSalas.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record DesativarSalaDTO(
        @NotNull Long id,
        @NotNull Boolean salaInativa) {
}
