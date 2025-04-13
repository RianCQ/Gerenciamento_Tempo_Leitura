package com.trabalho.leitura.model;

public class Livro {
    // Atributos do livro
    private Cliente dono;
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoPublicacao;
    private Integer numeroPaginas;
    private TempoLeitura tempo; // Tempo de leitura compõem o livro
    private boolean lido; // Indica se o livro foi lido ou não pelo cliente

    public Livro(String titulo, String autor, String editora, int anoPublicacao, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
        this.tempo = new TempoLeitura(this); // Inicializa o tempo de leitura com base no livro
        this.lido = false; // Inicialmente, o livro não foi lido
    }
    public Cliente getDono() {
        return dono; // Retorna o cliente que possui o livro
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getEditora() {
        return editora;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public TempoLeitura getTempo() {
        return tempo; // Retorna o tempo que compõe o livro
    }
    public boolean isLido() {
        return lido;
    }
    public void marcarComoLido() {
        this.lido = true;
    }
}
