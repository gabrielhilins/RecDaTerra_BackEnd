package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.*;
import com.example.projetopoobackendequipe4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    //@Autowired
    //private AvaliacaoRepository avaliacaoRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Long id) throws ClienteNaoEncontradoException {
       Optional<Cliente> optionalCliente = clienteRepository.findById(id);

       if (optionalCliente.isEmpty()) {
           throw new ClienteNaoEncontradoException("Cliente não encontrado com a id: " + id);
       }
       return optionalCliente.get();
    }

    public Cliente buscarClientePorEmail(String email) throws ClienteNaoEncontradoException {
        return clienteRepository.findByEmail(email)
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

    /*public void avaliarEvento(Long clienteId, Long eventoId, Byte nota, String comentario) throws ClienteNaoEncontradoException {
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
    }*/

    @Transactional
    public void avaliarAlgo(Long clienteId, Long algoAvaliavelId, Byte nota, String comentario, String tipo) 
            throws ClienteNaoEncontradoException, AvaliacaoNaoEncontradaException {

        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));

        Class<? extends Avaliavel> clazz = pegarTipoAvaliavel(tipo); //Chama-se o método para determinar o tipo de item a ser avaliado.
        if (clazz == null) {
            throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
        }

        avaliacaoService.avaliarAlgo(cliente, algoAvaliavelId, nota, comentario, clazz);
    }

    //Método responsável por retornar qual o tipo da entidade Cliente está avaliando.
    private Class<? extends Avaliavel> pegarTipoAvaliavel(String tipo) {
        switch (tipo.toUpperCase()) { //Converte o tipo maiúsculas para corresponder ao "DiscriminatorValue" das entidades.
            case "PRODUTO":
                return Produto.class;
            case "PRODUTOR":
                return Produtor.class;
            case "EVENTO":
                return Evento.class;
            default:
                return null;
        }
    }

}