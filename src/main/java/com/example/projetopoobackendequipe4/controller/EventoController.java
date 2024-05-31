package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.*;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Evento;
import com.example.projetopoobackendequipe4.service.AvaliacaoService;
import com.example.projetopoobackendequipe4.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/criar")
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        Evento novoEvento = eventoService.criarEvento(evento);
        return ResponseEntity.ok(novoEvento);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Evento>> obterTodosEventos() {
        List<Evento> eventos = eventoService.obterTodosEventos();
        return ResponseEntity.ok(eventos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Evento> obterEventoPorId(@PathVariable Long id) {
        try {
            Evento evento = eventoService.obterEventoPorId(id);
            return ResponseEntity.ok(evento);
        } catch (EventoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento detalhesDoEvento) {
        try {
            Evento eventoAtualizado = eventoService.atualizarEvento(id, detalhesDoEvento);
            return ResponseEntity.ok(eventoAtualizado);
        } catch (EventoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        try {
            eventoService.deletarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/avaliacoes")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoesDeEvento(@PathVariable Long eventoId) {
        try {
            List<Avaliacao> avaliacoes = eventoService.listarAvaliacoesDeEvento(eventoId);
            return ResponseEntity.ok(avaliacoes);
        } catch (ProdutoNaoEncontradoException | AvaliacoesEventoNaoEncontradasException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
