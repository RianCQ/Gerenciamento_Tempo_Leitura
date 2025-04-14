package com.trabalho.leitura.service;

import java.util.Map;

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
    public Map<Long, Livro> findLivrosByClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.buscarClientePorId(clienteId);
        return cliente.getLivros(); // Retorna a lista de livros do cliente
    }
    public Livro criarLivroParaCliente(Long clienteId, Livro livro) {
        try{
            Cliente cliente = clienteRepository.buscarClientePorId(clienteId);
            if(cliente == null){
                throw new IllegalArgumentException("Cliente não encontrado"); // Lança exceção se o cliente não for encontrado
            }
            // Verifica se o livro já existe no repositório
            Livro livroExistente = livroRepository.buscarLivroPorTituloEAutor(livro.getTitulo(), livro.getAutor(), livro.getEditora(), livro.getAnoPublicacao());
            if(livroExistente != null){
                livro.setId(livroExistente.getId()); // Se o livro já existe, reutiliza o ID existente
            } else{
                livroRepository.adicionarLivro(livro); // Adiciona o livro ao repositório se não existir
            }
            cliente.addLivro(livro); // Associa o livro ao cliente
            livro.setDono(cliente); // Define o cliente como dono do livro
            livro.setTempo(new TempoLeitura(livro));
            return livro; // Se o livro já existe, retorna o livro existente
        }catch(Exception e){
            throw new RuntimeException("Erro ao criar livro para cliente: " + e.getMessage(), e); // Lança exceção se ocorrer erro
        }
    }
    public Livro buscarLivroPorId(Long idCliente, Long idLivro) {
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
        Livro livro = cliente.getLivros().get(idLivro); // Busca o livro pelo ID
        return livro; // Retorna o livro do cliente
    }
    public Livro atualizarStatusLivro(Long idCliente, Long idLivro) {
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado"); // Lança exceção se o cliente não for encontrado
        }
        Livro livro = cliente.getLivros().get(idLivro); // Busca o livro pelo ID
        livro.marcarComoLido(); // Marca o livro como lido
        cliente.getLivros().put(livro.getId(), livro); // Atualiza o livro do cliente
        clienteRepository.atualizarCliente(cliente); // Atualiza o cliente no repositório
        return livro; // Retorna o livro atualizado
    }
    public Livro atualizarDataLeitura(Long clienteId, Long livroId) {
        Cliente cliente = clienteRepository.buscarClientePorId(clienteId);
        // Verifica se o cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        } else{
        Livro livro = cliente.getLivros().get(livroId); // Busca o livro pelo ID
        TempoLeitura newTime = new TempoLeitura(livro); // Cria um novo objeto TempoLeitura
        livro.setTempo(newTime); // Atualiza a data de leitura do livro
        cliente.setLivro(livro);
        clienteRepository.atualizarCliente(cliente); // Atualiza o cliente no repositório
        return livro; // Retorna o livro atualizado
        }
    }
    public void atualizarStatus(Long clienteId, Long livroId) {
        Cliente cliente = clienteRepository.buscarClientePorId(clienteId);
        Livro livro = cliente.getLivros().get(livroId); // Busca o livro pelo ID
        livro.marcarComoLido(); // Marca o livro como lido
        cliente.setLivro(livro); // Atualiza o livro do cliente
        clienteRepository.atualizarCliente(cliente); // Atualiza o cliente no repositório
    }
    public void deleteLivroParaCliente(Long idCliente, Long idLivro) {
        clienteRepository.buscarClientePorId(idCliente).removerLivro(livroRepository.buscarLivroPorId(idLivro)); // Remove o livro do cliente
    }
}
