package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("PRODUTOR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Produtor extends Usuario implements Avaliavel {
    // public class Produtor extends Usuario implements Avaliavel {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produtor_id", nullable = false)
    private Long id;

    @Column(name = "documento", length = 15, nullable = false)
    private String documento;

    @Column(name = "bio", length = 400)
    private String bio;

    @Column(name = "contato", length = 15)
    private String contato;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "rua", length = 100)
    private String rua;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "foto_perfil")
    private byte[] fotoPerfil;

    @OneToMany(mappedBy = "produtor", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @OneToMany(mappedBy = "produtorDestino", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;

    // @OneToMany(mappedBy = "produtorAvaliado", cascade = CascadeType.ALL)
    // private List<Avaliacao> avaliacoes; //Lista de avaliações que o produtor recebe de vários clientes

    private List<String> comentarios = new ArrayList<>(); //Lista de comentários que o produtor recebe de vários clientes

    //Referênciar/Pegar o "id" do Produtor ao "id" de Avaliavel, e explicitar/criar o "tipo" da entidade avaliada
    @Override
    public Long getIdAvaliavel() {
        return id;
    }
    @Override
    public String getTipo() {
        return "Produtor";
    }

}
