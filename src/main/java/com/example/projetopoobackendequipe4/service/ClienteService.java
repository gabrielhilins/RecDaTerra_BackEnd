package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.*;
import com.example.projetopoobackendequipe4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public Avaliacao avaliarAlgo(Cliente cliente, Long avaliavelId, Byte nota, String descricao, String tipo) throws AvaliacaoNaoEncontradaException {
        if(avaliavelId == null) {
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        avaliacaoService.diferenciarAlgoAvaliavel(avaliavelId, tipo);

        Avaliacao a = new Avaliacao();

        a.setNota(nota);
        a.setDescricao(descricao);
        a.setClienteAvaliador(cliente);
        a.setAvaliavelId(avaliavelId);
        a.setTipoAvaliavel(tipo);
        a.setDataHora(LocalDateTime.now());

        return avaliacaoRepository.save(a);
    }

}