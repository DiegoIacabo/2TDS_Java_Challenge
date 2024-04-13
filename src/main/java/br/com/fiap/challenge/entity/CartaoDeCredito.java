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
@Table(name = "TB_CARTAO")
public class CartaoDeCredito {

    @Id
    @GeneratedValue(generator = "SQ_CARTAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CARTAO", sequenceName = "SQ_CARTAO", allocationSize = 1)
    @Column(name = "ID_CARTAO")
    private Long id;

    @Column(name = "NUM_CARTAO")
    private String numero;

    private LocalDate dataVencimento;

    private String codSeguranca;

    private String senha;

    private Double limite;

    private Double fatura;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "CONTA",
            referencedColumnName = "ID_CONTA",
            foreignKey = @ForeignKey(
                    name = "FK_CARTAO_CONTA"
            )
    )
    private Conta conta;
}
