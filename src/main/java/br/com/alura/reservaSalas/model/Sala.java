package br.com.alura.reservaSalas.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private int capacidade;

    private boolean salaInativa;

    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas;

    public Sala(Long id, String numero, int capacidade, boolean salaInativa) {
        if (salaInativa == true){
            System.out.println("Sala inativa!");
        }
        if (capacidade <= 0){
            System.out.println("Capacidade invalida! \n a capacidade deve ser positivo");
            return;
        }
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
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
