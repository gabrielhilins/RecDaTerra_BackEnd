package com.example.projetopoobackendequipe4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public void criarAvaliacao(Avaliacao a) {
        avaliacaoRepository.save(a);
    }

    public void deletarAvaliacaoPelaId(Long id) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) {
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        avaliacaoRepository.delete(a);
    }

    public void atualizarAvaliacao(Long id, String novoComentario) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) { 
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        a.setComentario(novoComentario);
        avaliacaoRepository.save(a);
    }

    public Avaliacao pegarAvaliacaoPelaId(Long id) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) {
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        return a;
    }

    public List<Avaliacao> listaAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    /*@Transactional
    public Avaliacao avaliarAlgo(Long id, Byte nota, Class<? extends AvaliavelImpl> clazz) {
        AvaliavelImpl avaliavel = entityManager.find(clazz, id);
        if (avaliavel == null) {
            throw new ResourceNotFoundException(clazz.getSimpleName() + " não encontrado para o ID " + id);
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setAvaliavel(avaliavel);
        return avaliacaoRepository.save(avaliacao);
    }*/

    public double mediaAvaliacoes(List<Avaliacao> avaliacoes) throws AvaliacaoNaoEncontradaException {
        if(avaliacoes.isEmpty()) {
            throw new AvaliacaoNaoEncontradaException("Ainda não há avaliações.");
        }
        
        int totalNotas = 0;
        int totalAvaliacoes = 0;
        
        for(int i = 0; i < avaliacoes.size(); i++) {
            totalNotas += avaliacoes.get(i).getNota();
            totalAvaliacoes++;
        }

        return totalNotas / totalAvaliacoes;
    }
}