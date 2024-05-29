package com.example.projetopoobackendequipe4.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetopoobackendequipe4.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    @Query("SELECT AVG(a.nota) FROM Avaliacao a")
    public double mediaAvaliacoes();
}
