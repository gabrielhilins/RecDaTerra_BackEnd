package com.example.projetopoobackendequipe4.repository;

import java.util.Optional;

// imports

import com.example.projetopoobackendequipe4.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

   // Repositorio para armezanar por notificacao enviada
   Optional<Notificacao> findNotificacaoByConteudo(String conteudo);

}