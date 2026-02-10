package br.com.alura.reservaSalas.model;

import br.com.alura.reservaSalas.dto.AtualizarUsuarioDTO;
import br.com.alura.reservaSalas.dto.CadastrarUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    public Usuario(CadastrarUsuarioDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
    }

    public void atualizarDados(AtualizarUsuarioDTO dto){
        this.nome = dto.nome();
        this.email = dto.email();
    }

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
