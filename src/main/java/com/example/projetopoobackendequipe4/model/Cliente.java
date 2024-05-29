package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@DiscriminatorValue("CLIENTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cliente extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id", nullable = false)
    private Long id;

    @Column(name = "bio", length = 400)
    private String bio;

    @Column(name = "fotoPerfil")
    private byte[] fotoPerfil;

    @OneToMany(mappedBy = "clienteAvaliador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "clienteDestino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes;
}