package com.example.projetopoobackendequipe4.service;

import java.util.Optional;
import java.util.List;

import com.example.projetopoobackendequipe4.exception.ProdutoNaoEncontradoException;
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

    public List<Avaliacao> listarAvaliacoesDeProdutor(Long produtoId) {
        return avaliacaoService.listarAvaliacoesPorAvaliavel(produtoId, "Produto");
    }

}


