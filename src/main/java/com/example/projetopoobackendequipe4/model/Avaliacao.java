package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avaliacao_id", nullable = false)
    private Long id;

    @Column(name = "nota",length = 2, nullable = false)
    private int nota;

    @Column(name = "descricao",length = 400, nullable = true)
    private String descricao;

    @ManyToOne // Várias avaliações associadas a um único Cliente
    @JoinColumn(name = "clienteAvaliador", referencedColumnName = "cliente_id")
    private Cliente clienteAvaliador;

    @Column(name = "avaliavelId")
    private Long avaliavelId;

    @Column(name = "tipoAvaliavel")
    private String tipoAvaliavel;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @PrePersist
    private void prePersist() {
        if (avaliavelId == null) {
            // Gera um valor para avaliavelId. Você pode customizar essa lógica como quiser.
            // Aqui estamos apenas usando um valor randomico como exemplo.
            avaliavelId = System.currentTimeMillis(); // ou outra lógica para gerar o valor
        }

        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
    }
}