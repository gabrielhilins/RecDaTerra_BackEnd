package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Cliente;
import com.example.projetopoobackendequipe4.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/criar")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarTodosClientes() {
        List<Cliente> clientes = clienteService.listarTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email) {
        try {
            Cliente cliente = clienteService.buscarClientePorEmail(email);
            return ResponseEntity.ok(cliente);
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente novosDetalhesDoCliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, novosDetalhesDoCliente);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clienteId}/avaliar-algo")
    public ResponseEntity<Avaliacao> avaliarAlgo(
            @PathVariable Long clienteId,
            @RequestParam Long avaliavelId,
            @RequestParam Byte nota,
            @RequestParam String comentario,
            @RequestParam String tipo) {
        try {
            Cliente cliente = clienteService.buscarClientePorId(clienteId);
            Avaliacao avaliacao = clienteService.avaliarAlgo(cliente, avaliavelId, nota, comentario, tipo);
            return ResponseEntity.ok(avaliacao);
        } catch (ClienteNaoEncontradoException | AvaliacaoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

}