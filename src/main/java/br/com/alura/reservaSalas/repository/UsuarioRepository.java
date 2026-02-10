package br.com.alura.reservaSalas.repository;

import br.com.alura.reservaSalas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nomeUsuario);

    Boolean findByEmail(String emailUsuario);

    boolean existsByEmail(String emailUsuario);

    // criar JPA para verificação de dados repetidos
}
