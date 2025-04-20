/*package com.trabalho.leitura.controller;

import com.trabalho.leitura.model.Livro;
import com.trabalho.leitura.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    @Autowired
    private LivroRepository programRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = programRepository.listarLivros();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Long id) {
        Livro livro = programRepository.buscarLivroPorId(id);
        if(livro != null){
            return new ResponseEntity<>(livro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro newLivro) {
        Livro livroAdicionado = programRepository.adicionarLivro(newLivro);
        return new ResponseEntity<>(livroAdicionado, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        Livro livro = programRepository.atualizarLivro(livroAtualizado);
        if (livro != null) {
            return new ResponseEntity<>(livro, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }

    @PatchMapping("/atualizarStatus/{id}")
    public ResponseEntity<Livro> atualizarStatus(@PathVariable Long id) {
        Livro livro = programRepository.buscarLivroPorId(id);
        if (livro != null) {
            livro.marcarComoLido(); // Marca o livro como lido
            Livro livroSalvo = programRepository.adicionarLivro(livro); // Salvar o livro no repositório
            return new ResponseEntity<>(livroSalvo, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }
    
    @PatchMapping("/atualizarData/{id}")
    public ResponseEntity<Livro> atualizarDescricao(@PathVariable Long id){
        Livro livro = programRepository.buscarLivroPorId(id);
        if (livro != null) {
            livro.getTempo().novaDataFinal(); // Atualiza a data de leitura do livro
            Livro livroSalvo = programRepository.adicionarLivro(livro); // Salvar o livro no repositório
            return new ResponseEntity<>(livroSalvo, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Verifica se o livro existe antes de tentar excluí-lo
        if(programRepository.buscarLivroPorId(id) != null) {
            programRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content após a exclusão
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
    }
}*/
