package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.*;
import com.example.projetopoobackendequipe4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Cliente criarCliente(Cliente cliente) {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para criar o cliente
        return (Cliente) usuarioService.criarUsuario(cliente);
    }


    public Cliente buscarClientePorId(Long id) throws ClienteNaoEncontradoException {
       Optional<Cliente> optionalCliente = clienteRepository.findById(id);

       if (optionalCliente.isEmpty()) {
           throw new ClienteNaoEncontradoException("Cliente não encontrado com a id: " + id);
       }
       return optionalCliente.get();
    }

    public Cliente buscarClientePorEmail(String email) throws ClienteNaoEncontradoException {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para buscar o cliente
        return (Cliente) usuarioService.buscarUsuarioPorEmail(email)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o email: " + email));
    }

    public Cliente atualizarCliente(Long id, Cliente novosDetalhesDoCliente) throws ClienteNaoEncontradoException {

        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente nao encontrado com id " + id);
        }

        Cliente cli = optionalCliente.get();
        cli.setBio(novosDetalhesDoCliente.getBio());
        cli.setFotoPerfil(novosDetalhesDoCliente.getFotoPerfil());

        return clienteRepository.save(cli);
    }

    public void deletarCliente(Long id) throws ClienteNaoEncontradoException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente nao encontrado com id: " + id);
        }

        Cliente cli = optionalCliente.get();
        clienteRepository.delete(cli);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }
    public void avaliarEvento(Long clienteId, Long eventoId, Byte nota, String comentario) throws ClienteNaoEncontradoException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + clienteId));

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado com o ID: " + eventoId));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setClienteAvaliador(cliente);
        avaliacao.setEventoAvaliado(evento);
        avaliacao.setDataHora(LocalDateTime.now());
        avaliacao.setComentario(comentario);

        avaliacaoRepository.save(avaliacao);
    }

    public void avaliarProduto(Long clienteId, Long produtoId, Byte nota, String comentario) throws ClienteNaoEncontradoException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + clienteId));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoId));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setClienteAvaliador(cliente);
        avaliacao.setProdutoAvaliado(produto);
        avaliacao.setDataHora(LocalDateTime.now());
        avaliacao.setComentario(comentario);

        avaliacaoRepository.save(avaliacao);
    }

    public void avaliarProdutor(Long clienteId, Long produtorId, Byte nota, String comentario) throws ClienteNaoEncontradoException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + clienteId));

        Produtor produtor = produtorRepository.findById(produtorId)
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado com o ID: " + produtorId));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setClienteAvaliador(cliente);
        avaliacao.setProdutorAvaliado(produtor);
        avaliacao.setDataHora(LocalDateTime.now());
        avaliacao.setComentario(comentario);

        avaliacaoRepository.save(avaliacao);
    }


}