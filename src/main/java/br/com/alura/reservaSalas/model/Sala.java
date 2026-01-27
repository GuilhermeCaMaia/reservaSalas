package br.com.alura.reservaSalas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    private int capacidade;

    private boolean salaInativa;

    @OneToMany
    private Reserva reserva;

    public Sala(int id, String numero, int capacidade, boolean salaInativa) {
        if (salaInativa == true){
            System.out.println("Sala inativa!");
        }
        if (capacidade <= 0){
            System.out.println("Capacidade invalida! \n a capacidade deve ser positivo");
        }
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
    }

    public Sala() {}


}
