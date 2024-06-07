package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@DiscriminatorValue("NOTIFICACAO")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacao_id")
    private Long id;

    @Column(name = "conteudo", length = 100, nullable = false)
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "clienteDestino", referencedColumnName = "cliente_id")
    private Cliente clienteDestino;

    @ManyToOne
    @JoinColumn(name = "produtorDestino", referencedColumnName = "produtor_id")
    private Produtor produtorDestino;

    @Column(name = "dataHora", nullable = false)
    private LocalDateTime dataHora;

    @PrePersist
    protected void onCreate() {
        dataHora = LocalDateTime.now();
    }
}
