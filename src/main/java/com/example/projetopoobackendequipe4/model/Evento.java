package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
@DiscriminatorValue("EVENTO")
public class Evento implements Avaliavel {
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
    private List<Avaliacao> avaliacoes; //Lista de avaliações que o evento recebe de vários clientes

    @OneToMany(cascade = CascadeType.ALL)
    private List<String> comentarios = new ArrayList<>(); //Lista de cometários que o evento recebe de vários clientes
    
    @Column(length = 25)
    private LocalDateTime dataEvento;

    @Override
    public void adicionarComentario(String comentario) {
        comentarios.add(comentario);
    }

    @Override
    public void exibirComentario() {
        for(String comentario : comentarios) {
            System.out.print(comentario);
        }
    }

    @Override
    public void excluircomentario() {
        comentarios.clear();
    }

    @Override
    public void colocarNota(Byte nota) {
        Avaliacao a = new Avaliacao();
        a.setNota(nota);
        avaliacoes.add(a);
    }
}
