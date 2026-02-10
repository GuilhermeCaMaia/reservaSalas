package br.com.alura.reservaSalas.dto;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record CadastrarUsuarioDTO(
        @NotBlank (message = "O nome não pode estar em branco")
        String nome,
        @NotBlank (message = "O email não pode estar em branco")
        String email
) {
}
