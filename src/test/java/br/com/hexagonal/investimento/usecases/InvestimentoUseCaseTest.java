package br.com.hexagonal.investimento.usecases;

import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.domain.Investimento;
import br.com.hexagonal.investimento.mock.InvestimentoDtoMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class InvestimentoUseCaseTest {

    @Autowired
    private InvestimentoDtoMock investimentoDtoMock = new InvestimentoDtoMock();

    @Autowired
    private InvestimentoUseCase investimentoUseCase;

    @Test
    public void deveRetornarExecaoQuandoValorDoInvestimentoForZerado() {
        InvestimentoDto investimentoDto = investimentoDtoMock.getInvestimentoValorZero();
        assertThrows(Exception.class, () -> investimentoUseCase.realizaInvestimento(investimentoDto));
    }

    @Test
    public void deveRetornarFalseQuandoValorDoInvestimentoForZerado() {
        assertFalse(investimentoUseCase.validaValorDoInvestimento(investimentoDtoMock.getInvestimentoValorZero().getValor()));
    }

    @Test
    public void deveRetornarTrueQuandoValorDoInvestimentoNaoForZerado() {
        assertTrue(investimentoUseCase.validaValorDoInvestimento(investimentoDtoMock.getInvestimentoDto().getValor()));
    }

    @Test
    public void deveRetornarObjetoInvestimento() throws Exception {
        InvestimentoDto investimentoDto = investimentoDtoMock.getInvestimentoDto();
        Investimento investimento = investimentoUseCase.realizaInvestimento(investimentoDto);
        assertNotNull(investimento);
    }
}
