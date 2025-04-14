package com.trabalho.leitura.model;

import java.time.LocalDate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TempoLeitura {
    // Atributos do tempo de leitura
    private double cargaHoraria; // Tempo necessário de leitura total em minutos
    private LocalDate dataInicio; // Data de início da leitura 
    private LocalDate dataFinal; // Data final da leitura 

    public TempoLeitura(Livro livro) {
        //DecimalFormat df = new DecimalFormat("#.##");
        this.cargaHoraria = /*Double.parseDouble(df.format(*/livro.getNumeroPaginas() * (40.0/60.0); // Inicializa e calcula a carga horária total de leitura em minutos
        BigDecimal bd = new BigDecimal(cargaHoraria).setScale(2, RoundingMode.HALF_UP);
        this.cargaHoraria = bd.doubleValue();
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
