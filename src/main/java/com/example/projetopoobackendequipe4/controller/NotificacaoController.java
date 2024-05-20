package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.NotificacaoNaoEncontrada;
import com.example.projetopoobackendequipe4.model.Notificacao;
import com.example.projetopoobackendequipe4.service.NotificacaoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoservice;

    @GetMapping
    public ResponseEntity<List<Notificacao>> listarTodasNotificacoes(){
        List<Notificacao> notificacoes = notificacaoservice.listarTodasNotificacoes();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/(id)")
    public ResponseEntity<Notificacao> buscarNotificacaoPorId(@PathVariable long id){
        try{
            Notificacao notificacao = notificacaoservice.buscaPorId(id);
            return ResponseEntity.ok(notificacao);
        } catch (NotificacaoNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Notificacao> criarNotificacao(@RequestBody Notificacao notificacao){
        notificacaoservice.insertNotificacao(notificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacao);
    } 

    @PutMapping 
    public ResponseEntity<Notificacao> atualizarNotificacao(@PathVariable long id, Notificacao notificacao){
        try{
            Notificacao notificacaoAtualizada = notificacaoservice.atualizarNotificacao(id, notificacao);
            return ResponseEntity.ok(notificacaoAtualizada);
        } catch(NotificacaoNaoEncontrada e ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarNotificacao(@PathVariable long id){
        try{
            notificacaoservice.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }  catch(NotificacaoNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<Notificacao> buscarNotificacaoPorConteudo(@RequestParam String conteudo){
        try{
            Notificacao notificacao = notificacaoservice.buscaPorConteudo(conteudo);
            return ResponseEntity.ok(notificacao);
        } catch(NotificacaoNaoEncontrada e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}