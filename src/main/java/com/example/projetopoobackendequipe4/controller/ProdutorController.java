package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.ProdutorNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Produtor;
import com.example.projetopoobackendequipe4.service.ProdutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtores")
public class ProdutorController {

    @Autowired
    private ProdutorService produtorService;

    @GetMapping("/listar")
    public ResponseEntity<List<Produtor>> listarTodosProdutores() {
        List<Produtor> produtores = produtorService.listarTodosProdutores();
        return ResponseEntity.ok(produtores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtor> buscarProdutorPorId(@PathVariable Long id) {
        try {
            Produtor produtor = produtorService.buscarProdutorPeloId(id);
            return ResponseEntity.ok(produtor);
        } catch (ProdutorNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<Produtor> buscarProdutorPorDocumento(@PathVariable String documento) {
        try {
            Produtor produtor = produtorService.buscarProdutorPeloDocumento(documento);
            return ResponseEntity.ok(produtor);
        } catch (ProdutorNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Produtor> criarProdutor(@RequestBody Produtor produtor) {
        Produtor novoProdutor = produtorService.criarProdutor(produtor);
        return ResponseEntity.ok(novoProdutor);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produtor> atualizarProdutor(@PathVariable Long id, @RequestBody Produtor novosDetalhesDoProdutor) {
        try {
            Produtor produtorAtualizado = produtorService.atualizarProdutor(id, novosDetalhesDoProdutor);
            return ResponseEntity.ok(produtorAtualizado);
        } catch (ProdutorNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirProdutor(@PathVariable Long id) {
        try {
            produtorService.excluirProdutor(id);
            return ResponseEntity.noContent().build();
        } catch (ProdutorNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}