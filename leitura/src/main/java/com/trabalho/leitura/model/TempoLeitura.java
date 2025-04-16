package com.trabalho.leitura.model;

import java.time.LocalDate;

public class TempoLeitura {
    private double cargaHoraria; // Tempo de leitura total em minutos
    private LocalDate dataInicio; // Data de início da leitura no formato "dd/MM/yyyy"
    private LocalDate dataFinal; // Data final da leitura no formato "dd/MM/yyyy"

    public TempoLeitura(Livro livro) {
        this.cargaHoraria = livro.getNumeroPaginas() * (40.0/60.0) ; // Inicializa e calcula a carga horária total de leitura em minutos
        this.dataInicio = LocalDate.now(); // Data atual
        this.dataFinal = dataInicio.plusDays((long)(cargaHoraria/livro.getDono().getCargaDiaria())); // Data de término da leitura
    }

    public void novaDataFinal() {
        this.dataInicio = LocalDate.now(); // Data atual
        this.dataFinal = dataInicio.plusDays((long)cargaHoraria/1440); // Data de término da leitura
    }

    public double getTempoLeitura() {
        return cargaHoraria; // Retorna o tempo de leitura total em minutos
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFinal() {
        return dataFinal;
    }
}
