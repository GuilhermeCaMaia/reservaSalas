package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.Usuario;
import org.antlr.v4.runtime.misc.NotNull;

public record UsuarioDTO(
        @NotNull Long id,
        @NotNull String nome,
        @NotNull String email
) {
    public UsuarioDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
