package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CONTA")
public class Conta {

    @Id
    @GeneratedValue(generator = "SQ_CONTA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CONTA", sequenceName = "SQ_CONTA", allocationSize = 1)
    @Column(name = "ID_CONTA")
    private Long id;

    private Double saldo;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataEncerramento;

    @Column(name = "NUM_CONTA")
    private String numero;

    private String agencia;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "FK_CONTA_CLIENTE"
            )
    )
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "BANCO",
            referencedColumnName = "ID_BANCO",
            foreignKey = @ForeignKey(
                    name = "FK_CONTA_BANCO"
            )
    )
    private Banco banco;
}
