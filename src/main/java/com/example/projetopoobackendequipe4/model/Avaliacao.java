package com.example.projetopoobackendequipe4.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @Column(length = 1, nullable = false)
    private Byte nota;

    @Column(length = 400, nullable = true)
    private String comentario;

    @ManyToOne //Várias avaliações associadas a um único Cliente
    @JoinColumn(name = "clienteAvaliador", referencedColumnName  = "cliente_id")
    private Cliente clienteAvaliador;

    @ManyToOne //Várias avaliações de Clientes associadas a "algo avaliavel"
    @JoinColumn(name = "idAvaliavel", referencedColumnName  = "avaliavel_id")
    private Long avaliavelId;

    @ManyToOne
    @JoinColumn(name = "tipoAvaliado", referencedColumnName  = "tipo")
    private String tipoAvaliavel;

    @Column
    private LocalDateTime dataHora;
}
