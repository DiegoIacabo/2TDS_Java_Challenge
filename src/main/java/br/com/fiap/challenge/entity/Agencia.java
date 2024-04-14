package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_AGENCIA", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NUM_AGENCIA_BANCO",
                columnNames = {"BANCO", "NUM_AGENCIA"}
        )
})
public class Agencia {

    @Id
    @GeneratedValue(generator = "SQ_AGENCIA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGENCIA", sequenceName = "SQ_AGENCIA", allocationSize = 1)
    @Column(name = "ID_AGENCIA")
    private Long id;

    @Column(name = "NUM_AGENCIA", nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "BANCO",
            referencedColumnName = "ID_BANCO",
            foreignKey = @ForeignKey(
                    name = "FK_AGENCIA_BANCO"
            ),
            nullable = false
    )
    private Banco banco;
}
