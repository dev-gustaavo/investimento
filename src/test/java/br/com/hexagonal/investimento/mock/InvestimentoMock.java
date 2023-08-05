package br.com.hexagonal.investimento.mock;

import br.com.hexagonal.investimento.domain.Investimento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvestimentoMock {

    private Investimento investimento = new Investimento();

    public Investimento getInvestimentoMock() {
        this.investimento.setAtivo("GUTB3");
        this.investimento.setValor(BigDecimal.valueOf(10.50));
        this.investimento.setQuantidadeAcoes(100);
        return investimento;
    }

    public Investimento comValorZero() {
        this.investimento.setAtivo("GUTB3");
        this.investimento.setValor(BigDecimal.ZERO);
        this.investimento.setQuantidadeAcoes(100);
        return investimento;
    }
}
