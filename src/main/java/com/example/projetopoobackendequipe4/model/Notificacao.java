package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

//import Spring Boot
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.*;
//import

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Notificacao {
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Contéudo
    @Column(length = 100, nullable = false)
    private String conteudo;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usarioDestino;

    @Column(length= 15, nullable = false)
    private LocalDateTime dataHora;

}