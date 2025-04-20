package com.trabalho.leitura.model;

import java.time.LocalDate;

public class TempoLeitura {
    // Atributos do tempo de leitura
    private double cargaHoraria; // Tempo de leitura total em minutos
    private LocalDate dataInicio; // Data de início da leitura no formato "dd/MM/yyyy"
    private LocalDate dataFinal; // Data final da leitura no formato "dd/MM/yyyy"

    public TempoLeitura(Livro livro) {
        this.cargaHoraria = livro.getNumeroPaginas() * (40.0/60.0) ; // Inicializa e calcula a carga horária total de leitura em minutos
        this.dataInicio = LocalDate.now(); // Data atual
        this.dataFinal = dataInicio.plusDays((long)(cargaHoraria/livro.getDono().getCargaDiaria())); // Data de término da leitura
    }

    public void novaDataFinal(Livro livro) {
        this.dataInicio = LocalDate.now(); // Data atual
        this.dataFinal = dataInicio.plusDays((long)(cargaHoraria/livro.getDono().getCargaDiaria())); // Data de término da leitura
    }

    public double getTempoLeitura() {
        return cargaHoraria; // Retorna o tempo de leitura total em minutos
    }
    public LocalDate getDataInicio() {
        return dataInicio; // Retorna a data de início da leitura
    }
    public LocalDate getDataFinal() {
        return dataFinal; // Retorna a data final da leitura
    }
}
