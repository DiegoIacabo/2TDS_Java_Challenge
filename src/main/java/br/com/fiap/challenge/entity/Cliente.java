package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(generator = "SQ_CLIENTE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NM_CLIENTE")
    private String nome;

    private String cpf;

    private LocalDate nascimento;

    private Double renda;
}
