package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Evento;
import com.example.projetopoobackendequipe4.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    AvaliacaoService avaliacaoService;

    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> obterTodosEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> obterEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

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

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
    public List<Avaliacao> listarAvaliacoesDeProdutor(Long eventoId) {
        return avaliacaoService.listarAvaliacoesPorAvaliavel(eventoId, "Evento");
    }
}
