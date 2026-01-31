package br.com.alura.reservaSalas.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record AtualizarUsuarioDTO(
        @NotNull Long id,
        @NotNull String nome,
        @NotNull String email
) {
}
