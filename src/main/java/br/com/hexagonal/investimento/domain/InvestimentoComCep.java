package br.com.hexagonal.investimento.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestimentoComCep {

    private String ativo;
    private BigDecimal valor;
    private int quantidadeAcoes;
    private String cep;
    private String uf;
}
