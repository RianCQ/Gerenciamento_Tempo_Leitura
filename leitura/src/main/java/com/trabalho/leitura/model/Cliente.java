package com.trabalho.leitura.model;

import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private Long id; // ID do cliente
    private String nome;
    private String email;
    private Double cargaDiaria; // Carga horária diária de leitura para um livro em minutos
    private Map<Long,Livro> livros; // Lista de livros do cliente

    public Cliente(String nome, String email, Double cargaDiaria) {
        this.nome = nome;
        this.email = email;
        this.cargaDiaria = cargaDiaria; // Inicializa a carga horária diária de leitura
        this.livros = new HashMap<>();
    }

    public Long getId() {
        return id.longValue(); // Retorna o ID do cliente
    }

    public void setId(Long id) {
        this.id = id; // Atualiza o ID do cliente
    }

    public String getNome() {
        return nome; // Retorna o nome do cliente
    }

    public String getEmail() {
        return email; // Retorna o email do cliente
    }

    public Double getCargaDiaria() {
        return cargaDiaria; // Retorna a carga horária diária de leitura
    }

    public void setCargaDiaria(Double cargaDiaria) {
        this.cargaDiaria = cargaDiaria; // Atualiza a carga horária diária de leitura
    }

    public Map<Long, Livro> getLivros() {
        return livros; // Retorna a lista de livros do cliente
    }
    public void addLivro(Livro livro) {
        if(!this.livros.containsKey(livro.getId())){ // Verifica se o livro já está na lista
            this.livros.put(livro.getId(), livro); // Adiciona um livro à lista de livros do cliente
            livro.setDono(this); // Associa o cliente como dono do livro
        }
    }
    public void setLivro(Livro livro) {
        if (this.livros.containsKey(livro.getId())) { // Verifica se o livro já está na lista
            this.livros.put(livro.getId(), livro); // Atualiza o livro na lista de livros do cliente
            livro.setDono(this); // Associa o cliente como dono do livro
        }
    }
    public void removerLivro(Livro livro) {
        if (this.livros.containsKey(livro.getId())) { // Verifica se o livro está na lista
            this.livros.remove(livro.getId()); // Remove o livro da lista de livros do cliente
            livro.setDono(null); // Remove a associação do cliente com o livro
        }
    }
}
