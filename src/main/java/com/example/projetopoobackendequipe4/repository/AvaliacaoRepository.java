package com.example.projetopoobackendequipe4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetopoobackendequipe4.model.Avaliacao;
import com.example.projetopoobackendequipe4.model.Evento;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

    public List<Evento> destaqueSemanal();
}
