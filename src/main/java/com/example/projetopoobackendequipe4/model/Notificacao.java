package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

//import Spring Boot
import jakarta.persistence.*;
import lombok.*;
//import

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Notificacao {
    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
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

    @PrePersist
    protected void onCreate() {
        dataHora = LocalDateTime.now();
    }

}