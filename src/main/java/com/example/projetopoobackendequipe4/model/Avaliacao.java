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

    @ManyToOne //Várias avaliações associadas a um único Cliente
    @JoinColumn(name = "clienteAvaliador", referencedColumnName  = "cliente_id")
    private Cliente clienteAvaliador;

    @ManyToOne //Várias avaliações de Clientes associadas a um único Produto referenciado
    @JoinColumn(name = "produtoAvaliado", referencedColumnName  = "produto_id")
    private Produto produtoAvaliado;

    @ManyToOne ///Várias avaliações de Clientes associadas a um único Produtor referenciado
    @JoinColumn(name = "produtorAvaliado", referencedColumnName  = "produtor_id")
    private Produtor produtorAvaliado;

    @ManyToOne //Várias avaliações de Clientes associadas a um único Evento referenciado
    @JoinColumn(name = "eventoAvaliado", referencedColumnName  = "evento_id")
    private Evento eventoAvaliado;

    @Column
    private LocalDateTime dataHora;

    @Column(length = 400, nullable = true)
    private String comentario;
}
