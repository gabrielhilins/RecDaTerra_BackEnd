package com.example.projetopoobackendequipe4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetopoobackendequipe4.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
