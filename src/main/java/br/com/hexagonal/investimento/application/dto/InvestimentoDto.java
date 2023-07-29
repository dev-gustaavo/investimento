package br.com.hexagonal.investimento.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestimentoDto {

    private String ativo;
    private BigDecimal valor;
    private int quantidadeAcoes;
}
