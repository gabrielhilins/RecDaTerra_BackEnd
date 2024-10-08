package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.exception.AvaliacoesEventoNaoEncontradasException;
import com.example.projetopoobackendequipe4.exception.ProdutoNaoEncontradoException;
import com.example.projetopoobackendequipe4.exception.ProdutorNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Evento;
import com.example.projetopoobackendequipe4.model.Produto;
import com.example.projetopoobackendequipe4.model.Produtor;
import com.example.projetopoobackendequipe4.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    AvaliacaoService avaliacaoService;

    @Transactional
    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> obterTodosEventos() {
        return eventoRepository.findAll();
    }

    public Evento obterEventoPorId(Long id) {
        Optional<Evento> opEvento = eventoRepository.findById(id);

        if(opEvento.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto Não Encontrado");

        }

        Evento evento = opEvento.get();
        return(evento);
    }

    @Transactional
    public Evento atualizarEvento(Long id, Evento detalhesDoEvento) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        // Atualizando os campos do evento com os novos detalhes
        evento.setNomeEvento(detalhesDoEvento.getNomeEvento());
        evento.setInformacoes(detalhesDoEvento.getInformacoes());
        evento.setDescricao(detalhesDoEvento.getDescricao());
        evento.setSaibaMaisEvento(detalhesDoEvento.getSaibaMaisEvento());
        evento.setContato(detalhesDoEvento.getContato());
        evento.setDataEvento(detalhesDoEvento.getDataEvento());
        evento.setProdutores(detalhesDoEvento.getProdutores());

        return eventoRepository.save(evento);
    }

    @Transactional
    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
    public List<Avaliacao> listarAvaliacoesDeEvento(Long eventoId) {
        Evento evento = obterEventoPorId(eventoId);
        if (evento == null) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o ID: " + eventoId);
        }
        return avaliacaoService.listarAvaliacoesPorAvaliavel(eventoId, "Evento");
    }

    public double mediaAvaliacoesDeEvento(Long eventoId) throws AvaliacoesEventoNaoEncontradasException {
       List<Avaliacao> avaliacoes = listarAvaliacoesDeEvento(eventoId);
        if(avaliacoes.isEmpty()) {
            throw new AvaliacoesEventoNaoEncontradasException("Ainda não há avaliações desse Evento.");
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
