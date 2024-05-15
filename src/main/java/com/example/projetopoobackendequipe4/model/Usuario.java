package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", length = 5, nullable = false)
    private Long id;

    @Column(name = "nomeUsuario", length = 50, nullable = false)
    private String nomeUsuario;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;
    @Column(name = "dataRegistro" , length = 25, nullable = false)
    private LocalDateTime dataRegistro;
}
