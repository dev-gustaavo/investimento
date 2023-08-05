package br.com.hexagonal.investimento.adapters.outbound;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class InvestimentoEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ativo;
    private BigDecimal valor;
    private int quantidadeAcoes;
}
