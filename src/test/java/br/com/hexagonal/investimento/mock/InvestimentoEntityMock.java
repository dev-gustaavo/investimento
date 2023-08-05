package br.com.hexagonal.investimento.mock;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;

import java.math.BigDecimal;

public class InvestimentoEntityMock {

    private InvestimentoEntity investimentoEntity;

    public InvestimentoEntity InvestimentoEntityMock() {
        investimentoEntity = new InvestimentoEntity();
        investimentoEntity.setId(Long.valueOf(1));
        investimentoEntity.setAtivo("GUTB3");
        investimentoEntity.setValor(BigDecimal.valueOf(100));
        investimentoEntity.setQuantidadeAcoes(100);
        return investimentoEntity;
    }
}
