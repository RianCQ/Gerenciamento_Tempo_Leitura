package com.trabalho.leitura.repository;

import org.springframework.stereotype.Repository;
import com.trabalho.leitura.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
// Esta classe é responsável por gerenciar os dados dos livros
public class LivroRepository {
    private final Map<Long, Livro> livros = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Contador para gerar IDs únicos para os livros

    // Método que retorna uma lista com todos os livros cadastrados
    public List<Livro> listarLivros() {
        return new ArrayList<>(livros.values());
    }

    // Método que retorna um livro específico com base no ID
    public Livro buscarLivroPorId(Long id) {
        return livros.get(id);
    }

    // Método que adiciona um novo livro ao repositório
    public Livro adicionarLivro(Livro livro) {
        if(livro.getId() == null){
            livro.setId(idCounter.getAndIncrement()); // Gera um novo ID único
        }
        livros.put(livro.getId(), livro); // Adiciona o livro ao repositório com o ID gerado
        return livro; // Retorna o livro adicionado
    }

    // Método que atualiza um livro existente com base no ID
    public Livro atualizarLivro(Livro livroAtualizado) {
        if (livros.containsKey(livroAtualizado.getId())) { // Verifica se o livro existe
            livros.put(livroAtualizado.getId(), livroAtualizado); // Atualiza o livro no repositório
            return livroAtualizado; // Retorna o livro atualizado
        }
        return null; // Retorna null se o livro não foi encontrado
    }

    // Método que busca um livro específico com base no título e autor
    public Livro buscarLivroPorTituloEAutor(String titulo, String autor, String editora, Integer anoPublicacao) {
        return livros.values().stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo) && livro.getAutor().equalsIgnoreCase(autor) && livro.getEditora().equalsIgnoreCase(editora) && livro.getAnoPublicacao() == anoPublicacao)
                .findFirst()
                .orElse(null);
    }

    //Método que remove um livro existente com base no ID
    public void delete(Long id){
        livros.remove(id);
    }
}
