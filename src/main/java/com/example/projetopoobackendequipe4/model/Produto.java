package com.example.projetopoobackendequipe4.model;

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
//@DiscriminatorValue("PRODUTO")
public class Produto {

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
