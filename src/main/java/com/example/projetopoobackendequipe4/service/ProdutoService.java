package com.example.projetopoobackendequipe4.service;

import java.util.Optional;
import java.util.List;

import com.example.projetopoobackendequipe4.exception.AvaliacoesProdutoNaoEncotradasException;
import com.example.projetopoobackendequipe4.exception.AvaliacoesProdutorNaoEncontradasException;
import com.example.projetopoobackendequipe4.exception.ProdutoNaoEncontradoException;
import com.example.projetopoobackendequipe4.exception.ProdutorNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Produtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Produto;
import com.example.projetopoobackendequipe4.repository.ProdutoRepository;



@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private AvaliacaoService avaliacaoService;

    public void criarProduto(Produto p) {
        produtoRepository.save(p);
    }
    
    public void deletarProduto(Long id) throws ProdutoNaoEncontradoException {
        Optional<Produto>opProduto = produtoRepository.findById(id);

        if(opProduto.isEmpty()){
            throw new ProdutoNaoEncontradoException("Produto Não Encontrado");
        }

        produtoRepository.deleteById(id);
    }

public Produto atualizarProduto(Long Id, Produto p) throws ProdutoNaoEncontradoException {
        Optional<Produto> opProduto = produtoRepository.findById(Id);

        if(opProduto.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto Não Encontrado");

        }

        Produto produto = opProduto.get();
        produto.setNomeProduto(p.getNomeProduto());
        produto.setDescricao(p.getDescricao());
        produto.setCategoria(p.getCategoria());
        produto.setEstoque(p.getEstoque());
        produto.setFotoProduto(p.getFotoProduto());

        return produtoRepository.save(produto);
    }


    public List<Produto> listaProduto(){
        return produtoRepository.findAll();
    }

    public Produto encontrarProdutopelaId(Long Id) throws ProdutoNaoEncontradoException{
        Optional<Produto> opProduto = produtoRepository.findById(Id);

        if(opProduto.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto Não Encontrado");

        }

        Produto produto = opProduto.get();
        return(produto);
    } 

    public List<Avaliacao> listarAvaliacoesDeProduto(Long produtoId) {
        Produto produto = encontrarProdutopelaId(produtoId);
        if (produto == null) {
            throw new ProdutorNaoEncontradoException("Produto não encontrado com o ID: " + produtoId);
        }
        return avaliacaoService.listarAvaliacoesPorAvaliavel(produtoId, "Produto");
    }

    public double mediaAvaliacoesDeProduto(Long produtoId) throws AvaliacoesProdutoNaoEncotradasException {
        List<Avaliacao> avaliacoes = listarAvaliacoesDeProduto(produtoId);

        if(avaliacoes.isEmpty()) {
            throw new AvaliacoesProdutoNaoEncotradasException("Ainda não há avaliações para esse Produto.");
        }

        int totalNotas = 0;
        int totalAvaliacoes = 0;

        for(int i = 0; i < avaliacoes.size(); i++) {
            totalNotas += avaliacoes.get(i).getNota();
            totalAvaliacoes++;
        }

        return (double) totalNotas / totalAvaliacoes;
    }

}


