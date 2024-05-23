package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
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
//@DiscriminatorValue("EVENTO")
public class Evento{
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
    
    @ManyToMany
    private List<Produtor> produtores;
    
    @OneToMany(mappedBy = "eventoAvaliado", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;
    
    @Column(length = 25)
    private LocalDateTime dataEvento;

    /*@Override
    public void adicionarComentario(String comentario) {

    }

    @Override
    public void exibirComentario() {

    }

    @Override
    public void excluircomentario() {

    }

    public void colocarNota(Byte nota) {

    }*/
}
