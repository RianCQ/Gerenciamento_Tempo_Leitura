package com.trabalho.leitura.repository;

import org.springframework.stereotype.Repository;
import com.trabalho.leitura.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
// Esta classe é responsável por gerenciar os dados de clientes
public class ClienteRepository {
    private static final Map<Long, Cliente> clientes = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Contador para gerar IDs únicos para os clientes

    // Método que retorna uma lista com todos os clientes cadastrados
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    // Método que retorna um cliente específico com base no ID
    public Cliente buscarClientePorId(Long id) {
        return clientes.get(id);
    }

    // Método que adiciona um novo cliente ao repositório
    public Cliente adicionarCliente(Cliente cliente) {
        cliente.setId(idCounter.getAndIncrement()); // Gera um novo ID único
        if(cliente.getId() == null){
            cliente.setId(idCounter.getAndIncrement()); // Gera um novo ID único
        }
        clientes.put(cliente.getId(), cliente); // Adiciona o cliente ao repositório com o ID gerado
        return cliente; // Retorna o cliente adicionado
    }

    // Método que atualiza um cliente existente com base no ID
    public Cliente atualizarCliente(Cliente clienteAtualizado) {
        if (clientes.containsKey(clienteAtualizado.getId())) { // Verifica se o cliente existe
            clientes.put(clienteAtualizado.getId(), clienteAtualizado); // Atualiza o cliente no repositório
            return clienteAtualizado; // Retorna o cliente atualizado
        }
        return null; // Retorna null se o cliente não foi encontrado
    }

    //Método que remove um cliente existente com base no ID
    public void delete(Long id){
        clientes.remove(id);
    }
}
