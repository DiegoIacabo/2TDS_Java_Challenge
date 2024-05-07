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
@Table(name = "TB_CARTAO", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NUM_CARTAO",
                columnNames = "NUM_CARTAO"
        ),
        @UniqueConstraint(
                name = "UK_CLIENTE_BANDEIRA",
                columnNames = {"CLIENTE", "BANDEIRA"}
        )
})
public class CartaoDeCredito {

    @Id
    @GeneratedValue(generator = "SQ_CARTAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CARTAO", sequenceName = "SQ_CARTAO", allocationSize = 1)
    @Column(name = "ID_CARTAO")
    private Long id;

    @Column(name = "NUM_CARTAO", nullable = false)
    private String numero;

    @Column(name = "VALIDADE", nullable = false)
    private LocalDate validade;

    @Column(name = "CODIGO_DE_SEGURANCA", nullable = false)
    private String codSeguranca;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "BANDEIRA", nullable = false)
    private String bandeira;

    @Column(name = "LIMITE", nullable = false)
    private Double limite;

    @Column(name = "FATURA")
    private Double fatura;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "FK_CARTAO_CLIENTE"
            ),
            nullable = false
    )
    private Cliente cliente;

    //@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinColumn(
    //        name = "CONTA",
    //        referencedColumnName = "ID_CONTA",
    //        foreignKey = @ForeignKey(
    //                name = "FK_CARTAO_CONTA"
    //      ),
    //        nullable = false
    //)
    //private Conta conta;
}
