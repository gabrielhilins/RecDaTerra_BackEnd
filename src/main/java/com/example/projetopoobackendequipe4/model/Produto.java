package com.example.projetopoobackendequipe4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@DiscriminatorValue("PRODUTO")
public class Produto implements Avaliavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produtor_criador", referencedColumnName = "produtor_id")
    @JsonIgnore
    private Produtor produtorCriador;

    @Column(name = "nome_produto", length = 20, nullable = false)
    private String nomeProduto;

    @Column(name = "descricao", length = 100, nullable = true)
    private String descricao;

    @Column(name = "categoria",length = 30, nullable = false)
    private String categoria;

    @Column(name = "estoque",length = 10, nullable = false)
    private int estoque;

    @Column(name = "disponibilidade",nullable = false)
    private boolean disponibilidade;

    @Column(name = "foto_produto", nullable = false)
    private String fotoProduto;

    @Column(name = "data_registro_produto", nullable = false)
    private LocalDateTime dataRegistroProduto;

    // @OneToMany(mappedBy = "produtoAvaliado", cascade = CascadeType.ALL)
    // private List<Avaliacao> avaliacoes; //Lista de avaliações que o produto recebe de vários clientes

    // private List<String> comentarios = new ArrayList<>(); //Lista de comentários que o produto recebe de vários clientes

    //Referênciar/Pegar o "id" do Produto ao "id" de Avaliavel, e explicitar/criar o "tipo" da entidade avaliada
    @Override
    public Long getIdAvaliavel() {
        return id;
    }
    @Override
    public String getTipo() {
        return "Produto";
    }

    @PrePersist
    protected void onCreate() {
        dataRegistroProduto = LocalDateTime.now();
    }
}
