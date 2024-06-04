package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.AvaliacoesEventoNaoEncontradasException;
import com.example.projetopoobackendequipe4.exception.ProdutoNaoEncontradoException;
import com.example.projetopoobackendequipe4.exception.AvaliacoesProdutoNaoEncotradasException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Produto;
import com.example.projetopoobackendequipe4.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar")
    public ResponseEntity<Void> criarProduto(@RequestBody Produto produto) {
        produtoService.criarProduto(produto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (ProdutoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto novoProduto) {
        try {
            Produto produtoAtualizado = produtoService.atualizarProduto(id, novoProduto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (ProdutoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listaProduto();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> encontrarProdutoPeloId(@PathVariable Long id) {
        try {
            Produto produto = produtoService.encontrarProdutopelaId(id);
            return ResponseEntity.ok(produto);
        } catch (ProdutoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/avaliacoes")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoesDeProduto(@PathVariable Long produtoId) {
        try {
            List<Avaliacao> avaliacoes = produtoService.listarAvaliacoesDeProduto(produtoId);
            return ResponseEntity.ok(avaliacoes);
        } catch (ProdutoNaoEncontradoException | AvaliacoesProdutoNaoEncotradasException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{eventoId}/media-avaliacoes-produto")
    public ResponseEntity<Double> mediaAvaliacoesDeProduto(@PathVariable Long produtoId) {
        try {
            double media = produtoService.mediaAvaliacoesDeProduto(produtoId);
            return new ResponseEntity<>(media, HttpStatus.OK);
        } catch (AvaliacoesEventoNaoEncontradasException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
