package com.example.projetopoobackendequipe4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetopoobackendequipe4.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

    //List<Avaliacao> findByProduto(Produto produto);
    //List<Avaliacao> findByEvento(Evento evento);
    //List<Avaliacao> findByProdutor(Produtor produtor);
    public double avaliacaoAlgoAvaliavel();
    public double mediaAvaliacoes();
}
