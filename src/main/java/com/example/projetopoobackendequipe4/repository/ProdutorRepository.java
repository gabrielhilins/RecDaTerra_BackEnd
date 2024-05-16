package com.example.projetopoobackendequipe4.repository;

import com.example.projetopoobackendequipe4.model.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    Optional<Produtor> findProdutorByDocumento(String documento);
}
