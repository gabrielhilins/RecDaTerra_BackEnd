package com.example.projetopoobackendequipe4.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetopoobackendequipe4.model.Avaliacao;
import java.util.List;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    @Query("SELECT AVG(a.nota) FROM Avaliacao a")
    double mediaAvaliacoes();
    
    List<Avaliacao> findByAvaliavelIdAndTipoAvaliavel(Long avaliavelId, String tipoAvaliavel);
}
