package com.example.projetopoobackendequipe4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cliente extends Usuario {

    @Column(name = "bio", length = 400)
    private String bio;

    @Column(name = "fotoPerfil")
    private byte[] fotoPerfil;
}