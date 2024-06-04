package com.example.projetopoobackendequipe4.controller;


//  IMPORTS REFERENCIAS
import com.example.projetopoobackendequipe4.exception.NotificacaoNaoEncontrada;
import com.example.projetopoobackendequipe4.model.Notificacao;
import com.example.projetopoobackendequipe4.service.NotificacaoService;

// IMPORTS SPRING BOOT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Notificacao>> listarTodasNotificacoes() {
        List<Notificacao> notificacoes = notificacaoService.listarTodasNotificacoes();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Notificacao> buscarNotificacaoPorId(@PathVariable long id) {
        try {
            Notificacao notificacao = notificacaoService.buscaPorId(id);
            return ResponseEntity.ok(notificacao);
        } catch (NotificacaoNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Notificacao> criarNotificacao(@RequestBody Notificacao notificacao) {
        notificacaoService.insertNotificacao(notificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacao);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Notificacao> atualizarNotificacao(@PathVariable long id, @RequestBody Notificacao notificacao) {
        try {
            Notificacao notificacaoAtualizada = notificacaoService.atualizarNotificacao(id, notificacao);
            return ResponseEntity.ok(notificacaoAtualizada);
        } catch (NotificacaoNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable long id) {
        try {
            notificacaoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (NotificacaoNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscar/conteudo")
    public ResponseEntity<Notificacao> buscarNotificacaoPorConteudo(@RequestParam String conteudo) {
        try {
            Notificacao notificacao = notificacaoService.buscaPorConteudo(conteudo);
            return ResponseEntity.ok(notificacao);
        } catch (NotificacaoNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
