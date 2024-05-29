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
    
    /*
     * 
     @OneToMany(mappedBy = "algoAvaliavel", cascade = CascadeType.ALL)
     private List<Avaliacao> avaliacoes; //Lista de avaliações que o Evento recebe de vários Clientes
     */

    private List<String> comentarios = new ArrayList<>(); //Lista para armazenar os comentários que o Evento recebe de vários Clientes
    
    @Column(length = 25)
    private LocalDateTime dataEvento;
    
    //Referênciar/Pegar o "id" do Evento ao "id" de Avaliavel, e explicitar/criar o "tipo" da entidade avaliada
    @Override
    public Long getIdAvaliavel() {
        return id;
    }
    @Override
    public String getTipo() {
        return "Evento";
    }
}
