package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false)
    private int nota;

    @Column(length = 400, nullable = true)
    private String comentario;

    @ManyToOne // Várias avaliações associadas a um único Cliente
    @JoinColumn(name = "clienteAvaliador", referencedColumnName = "cliente_id")
    private Cliente clienteAvaliador;

    @Column(name = "idAvaliavel")
    private Long avaliavelId;

    @Column(name = "tipoAvaliado")
    private String tipoAvaliavel;

    @Column
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