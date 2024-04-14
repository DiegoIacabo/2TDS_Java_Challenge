package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CONTA", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NUM_CONTA_AGENCIA",
                columnNames = {"AGENCIA", "NUM_CONTA"}
        )
})
public class Conta {

    @Id
    @GeneratedValue(generator = "SQ_CONTA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CONTA", sequenceName = "SQ_CONTA", allocationSize = 1)
    @Column(name = "ID_CONTA")
    private Long id;

    @Column(name = "SALDO", nullable = false)
    private Double saldo;

    @Column(name = "DATA_ABERTURA", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "DATA_ENCERRAMENTO")
    private LocalDateTime dataEncerramento;

    @Column(name = "NUM_CONTA", nullable = false)
    private String numero;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_CLIENTE_CONTA",
            joinColumns = {
                    @JoinColumn(
                            name = "CONTA",
                            referencedColumnName = "ID_CONTA",
                            foreignKey = @ForeignKey(name = "FK_CONTA_CLIENTE")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "CLIENTE",
                            referencedColumnName = "ID_CLIENTE",
                            foreignKey = @ForeignKey(name = "FK_CLIENTE_CONTA")
                    )
            }
    )
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "AGENCIA",
            referencedColumnName = "ID_AGENCIA",
            foreignKey = @ForeignKey(
                    name = "FK_CONTA_AGENCIA"
            ),
            nullable = false
    )
    private Agencia agencia;
}
