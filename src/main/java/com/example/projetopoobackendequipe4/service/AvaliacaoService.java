package com.example.projetopoobackendequipe4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.repository.AvaliacaoRepository;
import com.example.projetopoobackendequipe4.repository.EventoRepository;
import com.example.projetopoobackendequipe4.repository.ProdutoRepository;
import com.example.projetopoobackendequipe4.repository.ProdutorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public Avaliacao criarAvaliacao(Avaliacao a) {
        return avaliacaoRepository.save(a);
    }

    @Transactional
    public void deletarAvaliacaoPelaId(Long id) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) {
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        avaliacaoRepository.delete(a);
    }

    @Transactional
    public Avaliacao atualizarAvaliacao(Long id, Avaliacao novaAvaliacao) throws AvaliacaoNaoEncontradaException {
        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(id);

        if(opAvaliacao.isEmpty()) { 
            throw new AvaliacaoNaoEncontradaException("Essa avaliação não existe.");
        }

        Avaliacao a = opAvaliacao.get();
        a.setDescricao(novaAvaliacao.getDescricao());
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

    public void diferenciarAlgoAvaliavel(Long avaliavelId, String tipo) throws NullPointerException {
        boolean existe = false;
        
        switch(tipo) {
            case "Produto":
                existe = produtoRepository.existsById(avaliavelId);
                break;
            case "Produtor":
                existe = produtorRepository.existsById(avaliavelId);
                break;
            case "Evento":
                existe = eventoRepository.existsById(avaliavelId);
                break;
            default:
                throw new NullPointerException("Tipo de entidade à ser avaliada não é válido.");
        }

        if(!existe) {
            throw new NullPointerException("Entidade à ser avaliada não existe.");
        }
    }

    public List<Avaliacao> listarAvaliacoesPorAvaliavel(Long avaliavelId, String tipoAvaliavel) {
        return avaliacaoRepository.findByAvaliavelIdAndTipoAvaliavel(avaliavelId, tipoAvaliavel);
    }
}