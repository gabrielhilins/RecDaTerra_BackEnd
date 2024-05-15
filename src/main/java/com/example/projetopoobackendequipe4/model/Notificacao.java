package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

//import Spring Boot
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Notificacao {
    //Id
    @id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long id;

    //Contéudo
    @Column(length = 100, nullable = false)
    private String conteudo;

    @ManyToOne
    private Usuario usarioDestino;

    @Column(length= 15, nullable = false)
    private LocalDateTime dataHora;





}
