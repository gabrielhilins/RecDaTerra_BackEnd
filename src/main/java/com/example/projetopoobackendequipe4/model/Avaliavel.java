package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Avaliavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avaliavel_id")
    private Long id;

    public abstract void adicionarComentario(String comentario);

    public abstract void exibirComentario();

    public abstract void excluirComentario();

    public abstract void colocarNota(Byte nota);
}
