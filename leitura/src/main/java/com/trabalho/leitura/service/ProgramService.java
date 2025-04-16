package com.trabalho.leitura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trabalho.leitura.model.Cliente;
import com.trabalho.leitura.model.Livro;
import com.trabalho.leitura.model.TempoLeitura;
import com.trabalho.leitura.repository.ClienteRepository;
import com.trabalho.leitura.repository.LivroRepository;

@Service
public class ProgramService {
    private final ClienteRepository clienteRepository;
    private final LivroRepository livroRepository;

    public ProgramService(ClienteRepository clienteRepository, LivroRepository livroRepository) {
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;
    }
    public List<Livro> findLivrosByClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.buscarClientePorId(clienteId);
        return cliente.getLivros(); // Retorna a lista de livros do cliente
    }
    public Livro criarLivroParaCliente(Long clienteId, Livro livro) {
        try{
            Cliente cliente = clienteRepository.buscarClientePorId(clienteId);
            if(cliente == null){
                throw new IllegalArgumentException("Cliente não encontrado"); // Lança exceção se o cliente não for encontrado
            }
            if(livroRepository.buscarLivroPorId(livro.getId()) == null){
                livroRepository.adicionarLivro(livro); // Adiciona o livro ao repositório se não existir
            }
            cliente.addLivro(livro); // Associa o livro ao cliente
            livro.setDono(cliente); // Associa o cliente como dono do livro
            livro.setTempo(new TempoLeitura(livro));
            return livro; // Se o livro já existe, retorna o livro existente
        }catch(Exception e){
            throw new RuntimeException("Erro ao criar livro para cliente: " + e.getMessage(), e); // Lança exceção se ocorrer erro
        }
    }
    public Livro buscarLivroPorId(Long idCliente, Long idLivro) {
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
        Livro livro = cliente.getLivros().get(idLivro.intValue()); // Busca o livro pelo ID
        return livro; // Retorna o livro do cliente
    }
    public Livro atualizarLivro(Long idCliente, Livro livroAtualizado) {
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
        cliente.getLivros().set(livroAtualizado.getId().intValue(), livroAtualizado); // Atualiza o livro do cliente
        return livroAtualizado; // Retorna o livro atualizado
    }
    public void deleteLivroParaCliente(Long idCliente, Long idLivro) {
        clienteRepository.buscarClientePorId(idCliente).removerLivro(livroRepository.buscarLivroPorId(idLivro)); // Remove o livro do cliente
    }
}
