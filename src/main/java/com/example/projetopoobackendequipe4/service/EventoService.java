package com.example.projetopoobackendequipe4.service;

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
        evento.setNomeEvento(detalhesDoEvento.getNomeEvento());
        evento.setLocal(detalhesDoEvento.getLocal());
        evento.setDataEvento(detalhesDoEvento.getDataEvento());
        return eventoRepository.save(evento);
    }

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
