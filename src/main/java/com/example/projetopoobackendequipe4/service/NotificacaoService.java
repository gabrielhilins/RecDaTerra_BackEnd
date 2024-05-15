package com.example.projetopoobackendequipe4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetopoobackendequipe4.model.Notificacao;
import com.example.projetopoobackendequipe4.repository.NotificacaoRepository;

@Service
public class NotificacaoService {
    
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public void insertNotificacao(Notificacao n) {
        notificacaoRepository.save(n);
    }

    public Notificacao encontraPorConteudo(String conteudo) {
        Optional<Notificacao> opNotificacao = notificacaoRepository.findByConteudo(conteudo);

        if (opNotificacao.isEmpty()) {
            System.out.println("Erro: Notificação não encontrada para o conteúdo fornecido.");
            return null; // ou você pode lançar uma exceção
        }

        return opNotificacao.get();
    }
}
