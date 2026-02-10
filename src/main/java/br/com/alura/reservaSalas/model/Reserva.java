package br.com.alura.reservaSalas.model;

import br.com.alura.reservaSalas.dto.AtualizarReservaDTO;
import br.com.alura.reservaSalas.dto.CriarReservaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    public Reserva(CriarReservaDTO dto, Sala sala, Usuario usuario) {
        if (dto.dataInicio() == null) {
            throw new IllegalArgumentException("informe a data de inicio");
        } else if (dto.dataFim() == null) {
            throw new IllegalArgumentException("informe a data de fim");
        } else if(dto.dataFim().isBefore(dto.dataInicio())){
            throw new IllegalArgumentException("Data fim invalida! \n a data final deve ser depois da data inicio");
        }

        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.status = dto.status();
        this.sala = sala;
        this.usuario = usuario;
    }

    public void atualizarReserva(AtualizarReservaDTO dto){
        if (dto.dataInicio() == null) {
            throw new IllegalArgumentException("informe a data de inicio");
        } else if (dto.dataFim() == null) {
            throw new IllegalArgumentException("informe a data de fim");
        } else if(dto.dataFim().isBefore(dto.dataInicio())){
            throw new IllegalArgumentException("Data fim invalida! \n a data final deve ser depois da data inicio");
        }

        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.status = dto.status();
    }

    public void cancelarReserva() {
        this.status = StatusReserva.CANCELADA;
    }

    public Reserva() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

}
