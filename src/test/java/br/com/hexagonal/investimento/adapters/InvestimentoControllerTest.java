package br.com.hexagonal.investimento.adapters;

import br.com.hexagonal.investimento.adapters.inbound.InvestimentoController;
import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.application.service.InvestimentoService;
import br.com.hexagonal.investimento.mock.InvestimentoDtoMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InvestimentoControllerTest {

    @Autowired
    private InvestimentoDtoMock investimentoDtoMock = new InvestimentoDtoMock();

    private InvestimentoService investimentoService;

    @Autowired
    private InvestimentoController investimentoController = new InvestimentoController(investimentoService);

    @Test
    public void deveRetornarObjetoInvestimento() throws Exception {
        when(investimentoService.realizaInvestimento(any())).thenReturn(new InvestimentoEntity());
        when(investimentoController.realizaInvestimento(any())).thenReturn(new InvestimentoEntity());
    }
}
