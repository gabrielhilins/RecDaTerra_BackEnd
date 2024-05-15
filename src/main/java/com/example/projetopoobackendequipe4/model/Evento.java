package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id", nullable = false)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nomeEvento;

    @Column(length = 500, nullable = false)
    private String informacoes;

    @Column(length = 1000)
    private String descricao;

    @Column(length = 500)
    private String saibaMaisEvento;

    @Column(length = 15)
    private String contato;
    
    @ManyToOne
    private List<Produtor> produtores;
    
    @OneToMany(mappedBy = "evento")
    private List<Avaliacao> avaliacoes;
    
    @Column(length = 25)
    private LocalDateTime dataEvento;
}
