package br.com.alura.reservaSalas.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record CadastrarUsuarioDTO(
        @NotNull String nome,
        @NotNull String email
) {
}
