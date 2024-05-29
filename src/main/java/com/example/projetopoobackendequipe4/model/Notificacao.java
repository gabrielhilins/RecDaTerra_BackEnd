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
import jakarta.persistence.Table;
import lombok.*;
//import

@Entity
@Table(name = "Notidicacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Notificacao {
    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacao_id", nullable = false)
    private Long id;

    // Contéudo
    @Column(length = 100, nullable = false)
    private String conteudo;

    /* 
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuarioDestino;
    */
    
    @ManyToOne
    @JoinColumn(name = "clienteDestino", referencedColumnName = "cliente_id")
    private Cliente clienteDestino;

    @ManyToOne
    @JoinColumn(name = "produtorDestino", referencedColumnName = "produtor_id")
    private Produtor produtorDestino;

    @Column(nullable = false)
    private LocalDateTime dataHora;

}