package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@DiscriminatorValue("PRODUTOR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Produtor extends Usuario {
    // public class Produtor extends Usuario implements Avaliavel {}

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

    @OneToMany(mappedBy = "produtor")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "produtor")
    private List<Notificacao> notificacoes;

    /*
    @Override
    public void adicionarComentario() {

    }

    @Override
    public void exibirComentario() {

    }

    @Override
    public void excluircomentario() {

    }
     */
}
