package com.trabalho.leitura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trabalho.leitura.model.Cliente;
import com.trabalho.leitura.model.Livro;
import com.trabalho.leitura.repository.ClienteRepository;
//import com.trabalho.leitura.repository.LivroRepository;

@Service
public class ProgramService {
    public static List<Livro> findLivrosByClienteId(Long clienteId) {
        Cliente cliente = ClienteRepository.buscarClientePorId(clienteId);
        return cliente.getLivros(); // Retorna a lista de livros do cliente
    }
}
