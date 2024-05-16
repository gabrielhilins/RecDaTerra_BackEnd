package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
import com.example.projetopoobackendequipe4.exception.ProdutorNaoEncontradoException;
import com.example.projetopoobackendequipe4.exception.UsuarioNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Cliente;
import com.example.projetopoobackendequipe4.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Long id) throws ClienteNaoEncontradoException {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }

        return clienteOptional.get();
    }

    public Cliente buscarClientePorEmail(String email) throws ClienteNaoEncontradoException {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
        if (clienteOptional.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o email: " + email);
        }
        return clienteOptional.get();
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) throws ClienteNaoEncontradoException {
        // Verifica se o cliente existe no banco de dados
        Optional<Cliente> clienteOptionalExistente = clienteRepository.findById(id);
        if (clienteOptionalExistente.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }

        Cliente clienteExistente = clienteOptionalExistente.get();
        BeanUtils.copyProperties(cliente, clienteExistente, "id");
        return clienteRepository.save(clienteExistente);
    }

    public void excluirCliente(Long id) throws ClienteNaoEncontradoException {
        // Verifica se o cliente existe no banco de dados
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

}
