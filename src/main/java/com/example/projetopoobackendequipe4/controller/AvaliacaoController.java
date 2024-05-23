package com.example.projetopoobackendequipe4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetopoobackendequipe4.exception.AvaliacaoNaoEncontradaException;
import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.repository.AvaliacaoRepository;
import com.example.projetopoobackendequipe4.service.AvaliacaoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value="/api/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @PostMapping("/criar") //URL do Endpoint que indica a criação de uma Avaliação.
    public Avaliacao criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.criarAvaliacao(avaliacao);
    }

    @GetMapping("/lista") //URL do Endpoint que indica a busca por todas as Avaliações.
    public List<Avaliacao> listarTodasAvaliacoes() {
        return avaliacaoService.listaAvaliacoes();
    }

    @GetMapping("/{id}") //URL do Endpoint que busca uma Avaliação pelo "id". 
    public Avaliacao getAvaliacaoPelaId(@PathVariable Long id) {
        try {
            return avaliacaoService.pegarAvaliacaoPelaId(id);
        } catch (AvaliacaoNaoEncontradaException a) {
            return null;
        }
    }

    @PutMapping("/atualizar/{id}") //URL do Endpoint que indica uma atualização da Avaliacao pelo "id".
    public Avaliacao atualizarPelaId(@PathVariable Long id, @RequestBody Avaliacao novaAvaliacao) {
        try {
            return avaliacaoService.atualizarAvaliacao(id, novaAvaliacao);
        } catch(AvaliacaoNaoEncontradaException a) {
            return null;
        }
    }

    @DeleteMapping("/excluir/{id}") //URL do Endpoint que indica a exclusão de uma avaliacao pelo "id"
    public void deletarAvaliacaoPelaId(@PathVariable Long id) throws AvaliacaoNaoEncontradaException {
        avaliacaoService.deletarAvaliacaoPelaId(id);
    }
}
