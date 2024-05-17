package com.example.projetopoobackendequipe4.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetopoobackendequipe4.exception.NotificacaoNaoEncontrada;
import com.example.projetopoobackendequipe4.model.Notificacao;
import com.example.projetopoobackendequipe4.repository.NotificacaoRepository;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    // Método para inserir uma nova notificação
    public void insertNotificacao(Notificacao notificacao) {
        notificacaoRepository.save(notificacao);
    }

    // Metodo para listar notificações
    public List<Notificacao> listarTodasNotificacao() {
        return notificacaoRepository.findAll();
    }

    // Método para encontrar uma notificação pelo conteúdo
    public Notificacao encontraPorConteudo(String conteudo) {
        Optional<Notificacao> opNotificacao = notificacaoRepository.findNotificacaoByConteudo(conteudo);
        return opNotificacao.orElseThrow(() -> new NotificacaoNaoEncontrada("Notificacao não encontrada"));
    }

    // Método para deletar Notificações por ID
    public void deletarPorId(Long id) {
        if (notificacaoRepository.existsById(id)) {
            notificacaoRepository.deleteById(id);
        } else {
            throw new NotificacaoNaoEncontrada("Notificacao não encontrada por Id");
        }
    }

    // Método para encontrar uma notificação por ID
    public Notificacao encontraPorId(Long id) {
        Optional<Notificacao> opNotificacao = notificacaoRepository.findById(id);
        return opNotificacao.orElseThrow(() -> new NotificacaoNaoEncontrada("Notificacão não encontrada"));
    }

    public Notificacao updateNotificacao(Long id, Notificacao novaNotificacao) {
        return notificacaoRepository.findById(id).map(notificacao -> {
            notificacao.setConteudo(novaNotificacao.getConteudo());
            notificacao.setDataHora(novaNotificacao.getDataHora());
            notificacao.setUsuarioDestino(novaNotificacao.getUsuarioDestino());
            return notificacaoRepository.save(notificacao);
        })
                .orElseThrow(() -> new NotificacaoNaoEncontrada("Notificação não encontrada"));
    }

}