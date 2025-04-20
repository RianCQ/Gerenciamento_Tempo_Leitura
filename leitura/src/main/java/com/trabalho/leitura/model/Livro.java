package com.trabalho.leitura.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Livro {
    // Atributos do livro
    @JsonIgnore // Ignora o campo dono durante a serialização/deserialização
    private Cliente dono;
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoPublicacao;
    private Integer numeroPaginas;
    private TempoLeitura tempo; // Tempo de leitura compõem o livro
    private boolean lido; // Indica se o livro foi lido ou não pelo cliente

    public Livro(String titulo, String autor, String editora, Integer anoPublicacao, Integer numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
        this.tempo = new TempoLeitura(this); // Inicializa o tempo de leitura com base no livro
        this.lido = false; // Inicialmente, o livro não foi lido
    }
    public Livro() {
        // Construtor padrão necessário para a desserialização
    }
    public Cliente getDono() {
        return dono; // Retorna o cliente que possui o livro
    }
    public void setDono(Cliente dono) {
        this.dono = dono; // Atualiza o cliente que possui o livro
    }
    public Long getId() {
        return id; // Retorna o ID do livro
    }
    public void setId(Long id){
        this.id = id; // Atualiza o ID do livro
    }
    public String getTitulo() {
        return titulo; // Retorna o título do livro
    }
    public String getAutor() {
        return autor; // Retorna o autor do livro
    }
    public String getEditora() {
        return editora; // Retorna a editora do livro
    }
    public int getAnoPublicacao() {
        return anoPublicacao; // Retorna o ano de publicação do livro
    }
    public int getNumeroPaginas() {
        return numeroPaginas; // Retorna o número de páginas do livro
    }
    public TempoLeitura getTempo() {
        return tempo; // Retorna o tempo que compõe o livro
    }
    public void setTempo(TempoLeitura tempo) {
        this.tempo = tempo; // Atualiza o tempo de leitura do livro
    }
    public boolean isLido() {
        return lido; // Retorna se o livro foi lido ou não
    }
    public void marcarComoLido() {
        this.lido = true; // Marca o livro como lido
    }
}
