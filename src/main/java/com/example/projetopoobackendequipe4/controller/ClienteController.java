package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.ClienteNaoEncontradoException;
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

    /*@PostMapping("/{clienteId}/avaliar-evento/{eventoId}")
    public ResponseEntity<Void> avaliarEvento(
            @PathVariable Long clienteId,
            @PathVariable Long eventoId,
            @RequestParam Byte nota,
            @RequestParam String comentario) {
        try {
            clienteService.avaliarEvento(clienteId, eventoId, nota, comentario);
            return ResponseEntity.ok().build();
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clienteId}/avaliar-produto/{produtoId}")
    public ResponseEntity<Void> avaliarProduto(
            @PathVariable Long clienteId,
            @PathVariable Long produtoId,
            @RequestParam Byte nota,
            @RequestParam String comentario) {
        try {
            clienteService.avaliarProduto(clienteId, produtoId, nota, comentario);
            return ResponseEntity.ok().build();
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clienteId}/avaliar-produtor/{produtorId}")
    public ResponseEntity<Void> avaliarProdutor(
            @PathVariable Long clienteId,
            @PathVariable Long produtorId,
            @RequestParam Byte nota,
            @RequestParam String comentario) {
        try {
            clienteService.avaliarProdutor(clienteId, produtorId, nota, comentario);
            return ResponseEntity.ok().build();
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

    
}