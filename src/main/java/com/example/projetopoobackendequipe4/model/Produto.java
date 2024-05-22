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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produtor_id", referencedColumnName = "id")
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
}
