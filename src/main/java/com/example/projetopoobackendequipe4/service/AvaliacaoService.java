package com.example.projetopoobackendequipe4.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Avaliavel;
import com.example.projetopoobackendequipe4.model.Cliente;
import com.example.projetopoobackendequipe4.repository.AvaliacaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AvaliacaoService {

    @PersistenceContext //Anotação que serve para injetar o "EntityManager", responsável por gerenciar entidades persistidas.
    private EntityManager entityManager; //Usado para realizar operações CRUD em entidades persistentes (objetos armazenados permanentemente no banco de dados da aplicação).

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criarAvaliacao(Avaliacao a) {
        return avaliacaoRepository.save(a);
    }

    public void deletarAvaliacaoPelaId(Long id) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) {
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        avaliacaoRepository.delete(a);
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao novaAvaliacao) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) { 
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        a.setComentario(novaAvaliacao.getComentario());
        a.setNota(novaAvaliacao.getNota());
        return avaliacaoRepository.save(a);
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

    @Transactional //Indica que todas as operações realizadas pelo método "avaliarAlgo" devem ser tratadas dentro de uma única transação. 
    public void avaliarAlgo(Cliente cliente, Long algoAvaliavelId, Byte nota, String comentario, Class<? extends Avaliavel> clazz) //"Class<? extends Avaliavel>" Faz com que possamos diferenciar as subclasses de Avaliavel e seus métodos.
            throws AvaliacaoNaoEncontradaException {

        Avaliavel algoAvaliavel = entityManager.find(clazz, algoAvaliavelId); //Usamos o "EntityManager" para buscar qual a entidade (Produto, Produtor ou Evento) foi especificada na variável "clazz".
        if (algoAvaliavel == null) {
            throw new AvaliacaoNaoEncontradaException("Entidade a ser avaliada não encontrada.");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setClienteAvaliador(cliente);
        avaliacao.setAlgoAvaliavel(algoAvaliavel);
        avaliacao.setDataHora(LocalDateTime.now());
        avaliacao.setComentario(comentario);

        avaliacaoRepository.save(avaliacao);
    }

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