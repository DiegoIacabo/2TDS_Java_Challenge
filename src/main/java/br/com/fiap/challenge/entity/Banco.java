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
@Table(name = "TB_BANCO")
public class Banco {

    @Id
    @GeneratedValue(generator = "SQ_BANCO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_BANCO", sequenceName = "SQ_BANCO", allocationSize = 1)
    @Column(name = "ID_BANCO")
    private Long id;

    @Column(name = "NM_BANCO")
    private String nome;

    private String cnpj;
}
