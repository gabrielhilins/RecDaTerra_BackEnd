package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Cliente;
import com.example.projetopoobackendequipe4.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para criar o cliente
        return (Cliente) usuarioService.criarUsuario(cliente);
    }

    public Cliente buscarClientePorId(Long id) throws ClienteNaoEncontradoException {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para buscar o cliente
        return (Cliente) usuarioService.buscarUsuarioPorId(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id));
    }

    public Cliente buscarClientePorEmail(String email) throws ClienteNaoEncontradoException {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para buscar o cliente
        return (Cliente) usuarioService.buscarUsuarioPorEmail(email)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o email: " + email));
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) throws ClienteNaoEncontradoException {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para atualizar o cliente
        // Aqui, copiamos as propriedades de cliente para um cliente existente buscado por ID
        Cliente clienteExistente = buscarClientePorId(id);
        BeanUtils.copyProperties(cliente, clienteExistente, "id");
        return (Cliente) usuarioService.atualizarUsuario(clienteExistente);
    }

    public void excluirCliente(Long id) throws ClienteNaoEncontradoException {
        // Como Cliente estende Usuario, podemos utilizar o UsuarioService para excluir o cliente
        usuarioService.excluirUsuario(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

}
