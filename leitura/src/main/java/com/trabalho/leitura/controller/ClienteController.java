package com.trabalho.leitura.controller;

import com.trabalho.leitura.model.Cliente;
import com.trabalho.leitura.model.Livro;
import com.trabalho.leitura.repository.ClienteRepository;
import com.trabalho.leitura.service.ProgramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProgramService programService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepository.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.buscarClientePorId(id);
        if(cliente != null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/livros")
    public ResponseEntity<Map<Long, Livro>> getLivrosDoCliente(@PathVariable Long id) {
        return ResponseEntity.ok(programService.findLivrosByClienteId(id));
    }
    @GetMapping("/{id}/livros/{livroId}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Long id, @PathVariable Long livroId) {
        Livro livro = programService.buscarLivroPorId(id, livroId);
         // Verifica se o livro existe antes de tentar atualizá-lo
        if(livro != null){
            return new ResponseEntity<>(livro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente newCliente) {
        // Verifica se o cliente já existe antes de tentar adicioná-lo
        for (Cliente cliente : clienteRepository.listarClientes()) {
            if (cliente.getEmail().equals(newCliente.getEmail())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT); // Retorna 409 se o cliente já existir
            }
        }
        // Adiciona o cliente ao repositório se não existir
        Cliente clienteAdicionado = clienteRepository.adicionarCliente(newCliente);
        return new ResponseEntity<>(clienteAdicionado, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/livros")
    public ResponseEntity<Livro> adicionarLivro(@PathVariable long id, @RequestBody Livro newLivro) {
        try{
            Livro livroAdicionado = programService.criarLivroParaCliente(id, newLivro);
            return new ResponseEntity<>(livroAdicionado, HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o cliente não for encontrado
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 se ocorrer erro
        }
    }
    @PatchMapping("{id}/atualizarCargaLeitura/{newCarga}")
    public ResponseEntity<Cliente> atualizarCargaHoraria(@PathVariable Long id, @PathVariable Double newCarga){
        Cliente cliente = clienteRepository.buscarClientePorId(id);
        if (cliente != null) {
            cliente.setCargaDiaria(newCarga); // Atualiza a carga diária de leitura para um livro
            Cliente clienteSalvo = clienteRepository.atualizarCliente(cliente); // Salvar o livro no repositório
            return new ResponseEntity<>(clienteSalvo, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }
    @PatchMapping("{id}/atualizarStatus/{livroId}")
    public ResponseEntity<Livro> atualizarStatus(@PathVariable Long id, @PathVariable Long livroId) {
        Livro livroAtualizado = programService.atualizarStatusLivro(id, livroId);
         // Verifica se o livro existe antes de tentar atualizá-lo
        if (livroAtualizado != null) {
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }
    @PutMapping("{id}/atualizarData/{livroId}")
    public ResponseEntity<Livro> atualizarData(@PathVariable Long id, @PathVariable Long livroId){
        Livro livro = programService.atualizarDataLeitura(id, livroId);
        if (livro != null) {
            return new ResponseEntity<>(livro, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }
    @DeleteMapping("{id}/deleteLivro/{livroId}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long livroId) {
        // Verifica se o livro existe antes de tentar excluí-lo
        if(programService.buscarLivroPorId(id, livroId) != null) {
            programService.deleteLivroParaCliente(id, livroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content após a exclusão
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Verifica se o livro existe antes de tentar excluí-lo
        if(clienteRepository.buscarClientePorId(id) != null) {
            clienteRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content após a exclusão
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
    }
}
