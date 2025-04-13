package com.trabalho.leitura.model;

import java.util.List;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Cliente {
    private Long id; // ID do cliente
    private String nome;
    private String email;
    private Double cargaDiaria; // Carga horária diária de leitura para um livro em minutos
    private List<Livro> livros; // Lista de livros do cliente

    public Cliente(String nome, String email, Double cargaDiaria) {
        this.nome = nome;
        this.email = email;
        this.cargaDiaria = cargaDiaria; // Inicializa a carga horária diária de leitura
    }

    public Long getId() {
        return id.longValue(); // Retorna o ID do cliente
    }
    public void setId(Long id) {
        this.id = id; // Atualiza o ID do cliente
    }
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Double getCargaDiaria() {
        return cargaDiaria;
    }

    public void setCargaDiaria(Double cargaDiaria) {
        this.cargaDiaria = cargaDiaria; // Atualiza a carga horária diária de leitura
    }

    public List<Livro> getLivros() {
        return livros; // Retorna a lista de livros do cliente
    }
    public void adicionarLivro(Livro livro) {
        this.livros.add(livro); // Adiciona um livro à lista de livros do cliente
    }
    public void removerLivro(Livro livro) {
        this.livros.remove(livro); // Remove um livro da lista de livros do cliente
    }

    public void imprimirDados() {
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
        // String dataFormatada = dateFormat.format(dataNascimento); // Formata a data de nascimento
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //String text = dataNascimento.format(formatter);
        //LocalDate parsedDate = LocalDate.parse(text, formatter);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        //System.out.println("Data de Nascimento: " + parsedDate.format(formatter));
        System.out.println("Carga Diária de Leitura: " + cargaDiaria + " minutos");
    }
}
