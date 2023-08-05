package br.com.hexagonal.investimento.usecases;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.application.ports.InvestimentoOutbountPort;
import br.com.hexagonal.investimento.domain.Investimento;
import br.com.hexagonal.investimento.mock.InvestimentoDtoMock;
import br.com.hexagonal.investimento.mock.InvestimentoEntityMock;
import br.com.hexagonal.investimento.mock.InvestimentoMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InvestimentoUseCaseTest {

    @Mock
    InvestimentoDtoMock investimentoDtoMock;

    @Mock
    InvestimentoMock investimentoMock;

    @Autowired
    private InvestimentoUseCase investimentoUseCase;

    @Mock
    InvestimentoOutbountPort investimentoOutbountPort;

    @Mock
    ModelMapper mapper;

    @Mock
    InvestimentoEntityMock investimentoEntityMock;

    @Test
    public void deveRetornarExecaoQuandoValorDoInvestimentoForZerado() {
        InvestimentoDto investimentoDto = investimentoDtoMock.getInvestimentoValorZero();
        assertThrows(Exception.class, () -> investimentoUseCase.realizaInvestimento(investimentoDto));
    }

    @Test
    public void deveRetornarFalseQuandoValorDoInvestimentoForZerado() {
        Investimento investimento = investimentoMock.comValorZero();
        assertFalse(investimentoUseCase.validaValorDoInvestimento(investimentoMock.comValorZero().getValor()));
    }

    @Test
    public void deveRetornarTrueQuandoValorDoInvestimentoNaoForZerado() {
        assertTrue(investimentoUseCase.validaValorDoInvestimento(investimentoMock.getInvestimentoMock().getValor()));
    }

    @Test
    public void deveRetornarObjetoInvestimento() throws Exception {
        InvestimentoDto investimentoDto = investimentoDtoMock.getInvestimentoDto();
        InvestimentoEntity investimentoEnMock = investimentoEntityMock.InvestimentoEntityMock();
        Investimento investMock = investimentoMock.getInvestimentoMock();

        when(mapper.map(any(), any()))
                .thenReturn(investMock);

        when(investimentoOutbountPort.cadastraInvestimento(any()))
                .thenReturn(investimentoEnMock);

        InvestimentoEntity investimento = investimentoUseCase.realizaInvestimento(investimentoDto);
        assertNotNull(investimento);
    }

    @Test
    public void deveRetornarTrueQuandoForHorarioDeInvestimento() {
        boolean horarioInvestimento = investimentoUseCase.isHorarioDeInvestimento();

        LocalTime horaInicial = LocalTime.of(8, 0);
        LocalTime horaFinal = LocalTime.of(18, 0);
        LocalTime horario = LocalTime.now();

        if (horario.isAfter(horaInicial) && horario.isBefore(horaFinal))
            assertFalse(horarioInvestimento);
        else
            assertTrue(horarioInvestimento);
    }

    @Test
    public void deveRetornarExcecaoQuandoNaoForHorarioDeInvestimento() throws Exception {

        Investimento investimentoDto = investimentoMock.comValorZero();

        LocalTime horaInicial = LocalTime.of(8, 0);
        LocalTime horaFinal = LocalTime.of(18, 0);
        LocalTime horario = LocalTime.now();

        if (!horario.isAfter(horaInicial) && !horario.isBefore(horaFinal))
            assertThrows(Exception.class, () -> investimentoUseCase.executaRegrasDeNegocio(investimentoDto));
    }
}
