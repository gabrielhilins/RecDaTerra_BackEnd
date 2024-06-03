package com.example.projetopoobackendequipe4.model;

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
    @JoinColumn(name = "produtor", referencedColumnName = "produtor_id")
    private Produtor produtor;

    @Column(length = 20, nullable = false)
    private String nomeProduto;

    @Column(length = 100, nullable = true)
    private String descricao;

    @Column(length = 30, nullable = false)
    private String categoria;

    @Column(length = 10, nullable = true)
    private int estoque;

    @Column(nullable = false)
    private boolean disponibilidade;

    @Column(nullable = true)
    private byte[] fotoProduto;

    @Column(nullable = false)
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
