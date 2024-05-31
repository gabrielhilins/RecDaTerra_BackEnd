package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@DiscriminatorColumn(name = "tipo_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Usuario {

    @Column(name = "nomeUsuario", length = 50, nullable = false)
    private String nomeUsuario;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;
    @Column(name = "dataRegistro" , length = 25, nullable = false)
    private LocalDateTime dataRegistro;

    @PrePersist
    protected void onCreate() {
        dataRegistro = LocalDateTime.now();
    }
}
