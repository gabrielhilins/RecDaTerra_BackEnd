package com.example.projetopoobackendequipe4.model;


//import
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  //Inicio da classe Notificação
public class Notificacao {
    //Id
    @id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long id;

    //Contéudo
    @Column(length = 100, nullable = false)
    private String conteudo;

    //Usuario que a notificação chegará
    @Column(length = 20, nullable = false)
    private Usuario usarioDestino;

    //Data e horario que a notificação chega
    @Column(length= 15, nullable = false)
    private LocalDateTime dataHora;

    



}
