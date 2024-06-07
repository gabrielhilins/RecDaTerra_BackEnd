// ProdutorService.java
package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.exception.AvaliacoesProdutorNaoEncontradasException;
import com.example.projetopoobackendequipe4.exception.ProdutorNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Produtor;
import com.example.projetopoobackendequipe4.repository.ProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutorService {

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    public List<Produtor> listarTodosProdutores() {
        return produtorRepository.findAll();
    }

    public Produtor buscarProdutorPeloId(Long id) {
        Optional<Produtor> produtorOptional = produtorRepository.findById(id);
        if (produtorOptional.isEmpty()) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o id: " + id);
        }

        return produtorOptional.get();
    }

    public Produtor buscarProdutorPeloDocumento(String documento) {
        Optional<Produtor> produtorOptional = produtorRepository.findProdutorByDocumento(documento);
        if (produtorOptional.isEmpty()) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o documento: " + documento);
        }
       return produtorOptional.get();
    }

    @Transactional
    public Produtor criarProdutor(Produtor produtor) {
        return produtorRepository.save(produtor);
    }

    @Transactional
    public Produtor atualizarProdutor(Long id, Produtor novosDetalhesDoProdutor) throws ProdutorNaoEncontradoException {
        Optional<Produtor> produtorOptional = produtorRepository.findById(id);

        if (produtorOptional.isEmpty()) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o ID: " + id);
        }

        Produtor produtor = produtorOptional.get();
        produtor.setNomeUsuario(novosDetalhesDoProdutor.getNomeUsuario());
        produtor.setBio(novosDetalhesDoProdutor.getBio());
        produtor.setContato(produtor.getContato());
        produtor.setDocumento(novosDetalhesDoProdutor.getDocumento());
        produtor.setCep(novosDetalhesDoProdutor.getCep());
        produtor.setFotoPerfil(novosDetalhesDoProdutor.getFotoPerfil());

        return produtorRepository.save(produtor);
    }

    @Transactional
    public void excluirProdutor(Long id) throws ProdutorNaoEncontradoException {
        if (!produtorRepository.existsById(id)) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o ID: " + id);
        }
        produtorRepository.deleteById(id);
    }
    public List<Avaliacao> listarAvaliacoesDeProdutor(Long produtorId) throws ProdutorNaoEncontradoException{
        Produtor produtor = buscarProdutorPeloId(produtorId);
        if (produtor == null) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o ID: " + produtorId);
        }
        return avaliacaoService.listarAvaliacoesPorAvaliavel(produtorId, "Produtor");
    }
    public double mediaAvaliacoesDeProdutor(Long produtorId) throws AvaliacoesProdutorNaoEncontradasException {
        List<Avaliacao> avaliacoes = listarAvaliacoesDeProdutor(produtorId);

        if(avaliacoes.isEmpty()) {
            throw new AvaliacoesProdutorNaoEncontradasException("Ainda não há avaliações para esse Produto.");
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
