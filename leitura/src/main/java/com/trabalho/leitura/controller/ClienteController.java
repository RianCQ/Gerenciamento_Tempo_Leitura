package com.trabalho.leitura.controller;

import com.trabalho.leitura.model.Cliente;
import com.trabalho.leitura.model.Livro;
import com.trabalho.leitura.repository.ClienteRepository;
import com.trabalho.leitura.service.ProgramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepository.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Cliente cliente = ClienteRepository.buscarClientePorId(id);
        if(cliente != null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/livros")
    public ResponseEntity<List<Livro>> getLivrosDoCliente(@PathVariable Long id) {
        return ResponseEntity.ok(ProgramService.findLivrosByClienteId(id));
    }
    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente newCliente) {
        Cliente clienteAdicionado = clienteRepository.adicionarCliente(newCliente);
        return new ResponseEntity<>(clienteAdicionado, HttpStatus.CREATED);
    }
    //Errado
    @PatchMapping("/atualizarCargaLeitura/{id}/{newCarga}")
    public ResponseEntity<Cliente> atualizarDescricao(@PathVariable Long id, @PathVariable Double newCarga){
        Cliente cliente = ClienteRepository.buscarClientePorId(id);
        if (cliente != null) {
            cliente.setCargaDiaria(newCarga); // Atualiza a carga diária de leitura para um livro
            Cliente clienteSalvo = clienteRepository.adicionarCliente(cliente); // Salvar o livro no repositório
            return new ResponseEntity<>(clienteSalvo, HttpStatus.OK); // Retorna o livro atualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Verifica se o livro existe antes de tentar excluí-lo
        if(ClienteRepository.buscarClientePorId(id) != null) {
            clienteRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content após a exclusão
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o livro não for encontrado
    }
}
