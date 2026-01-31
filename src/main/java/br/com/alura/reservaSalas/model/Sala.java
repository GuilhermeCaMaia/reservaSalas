package br.com.alura.reservaSalas.model;

import br.com.alura.reservaSalas.dto.AtualizarSalaDTO;
import br.com.alura.reservaSalas.dto.CadastrarSalaDTO;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String numero;
    @NotNull
    private Integer capacidade;
    @NotNull
    private boolean salaInativa;

    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas;

    public Sala(CadastrarSalaDTO dto) {
        if (dto.salaInativa() == true){
            System.out.println("Sala inativa!");
        }
        if (dto.capacidade() <= 0){
            throw new IllegalArgumentException("Capacidade invalida! \n a capacidade deve ser positivo");
        }
        this.numero = dto.numero();
        this.capacidade = dto.capacidade();
        this.salaInativa = dto.salaInativa();
    }

    public void atualizarDados(AtualizarSalaDTO dto) {
        if (dto.salaInativa() == true){
            System.out.println("Sala inativa!");
        }
        if (dto.capacidade() <= 0){
            throw new IllegalArgumentException("Capacidade invalida! \n a capacidade deve ser positivo");
        }
        this.numero = dto.numero();
        this.capacidade = dto.capacidade();
        this.salaInativa = dto.salaInativa();
    }

    public Sala() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isSalaInativa() {
        return salaInativa;
    }

    public void setSalaInativa(boolean salaInativa) {
        this.salaInativa = salaInativa;
    }
}
