package br.com.alura.reservaSalas.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    public Reserva(int id, LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null) {
            throw new IllegalArgumentException("informe a data de inicio");
        } else if (dataFim == null) {
            throw new IllegalArgumentException("informe a data de fim");
        } else if(dataFim.isBefore(dataInicio)){
            throw new IllegalArgumentException("Data fim invalida! \n a data final deve ser depois da data inicio");
        }

        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Reserva() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
