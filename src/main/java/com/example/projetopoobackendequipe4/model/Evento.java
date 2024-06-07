package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "evento")
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

    @Column(name = "nomeEvento", length = 150, nullable = false)
    private String nomeEvento;

    @Column(name = "informacoes",length = 500, nullable = false)
    private String informacoes;

    @Column(name = "descricao",length = 1000)
    private String descricao;

    @Column(name = "saibaMaisEvento",length = 500)
    private String saibaMaisEvento;

    @Column(name = "fotoEvento")
    private String fotoEvento;

    @Column(name = "contato",length = 15)
    private String contato;
    
    @ManyToMany
    @JsonIgnore
    private List<Produtor> produtores;
    
    /*
     * 
     @OneToMany(mappedBy = "algoAvaliavel", cascade = CascadeType.ALL)
     private List<Avaliacao> avaliacoes; //Lista de avaliações que o Evento recebe de vários Clientes
     */

    // private List<String> comentarios = new ArrayList<>(); //Lista para armazenar os comentários que o Evento recebe de vários Clientes
    
    @Column(name = "dataEvento",length = 25)
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

    @PrePersist
    protected void onCreate() {
        dataEvento = LocalDateTime.now();
    }
}
