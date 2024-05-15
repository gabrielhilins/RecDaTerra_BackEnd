package com.example.projetopoobackendequipe4.repository;

import java.util.List;

// imports
import org.springframewok.data.jpa.repository.JpaRepository;
import org.springframewok.stereotype.Repository;

import com.example.projetopoobackendequipe4.model.Notificacao;


@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>{

   //Repositorio para armezanar por notificacao enviada 
List<Notificacao> encontraPorConteudo(String conteudo);


}
