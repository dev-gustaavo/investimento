package br.com.hexagonal.investimento.mock;

import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvestimentoDtoMock {

    private InvestimentoDto investimentoDto = new InvestimentoDto();

    public InvestimentoDto getInvestimentoDto() {
        this.investimentoDto.setAtivo("GUTB3");
        this.investimentoDto.setValor(BigDecimal.valueOf(10.50));
        this.investimentoDto.setQuantidadeAcoes(100);
        return investimentoDto;
    }

    public InvestimentoDto getInvestimentoValorZero() {
        this.investimentoDto.setAtivo("GUTB3");
        this.investimentoDto.setValor(BigDecimal.ZERO);
        this.investimentoDto.setQuantidadeAcoes(100);
        return investimentoDto;
    }
}
