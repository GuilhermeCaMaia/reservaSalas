package br.com.alura.reservaSalas.dto;

import br.com.alura.reservaSalas.model.Sala;

public record SalaDTO(
        Long id,
        String numero,
        Integer capacidade,
        boolean salaInativa
) {
    public SalaDTO(Sala sala){
        this(
                sala.getId(),
                sala.getNumero(),
                sala.getCapacidade(),
                sala.isSalaInativa()
        );
    }
}
