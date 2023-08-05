package br.com.hexagonal.investimento.usecases;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.application.ports.InvestimentoOutbountPort;
import br.com.hexagonal.investimento.application.service.InvestimentoService;
import br.com.hexagonal.investimento.domain.Investimento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
public class InvestimentoUseCase implements InvestimentoService {

    private final InvestimentoOutbountPort investimentoOutbountPort;

    private LocalTime horaInicial   = LocalTime.of(8, 0);
    private LocalTime horaFinal     = LocalTime.of(18, 0);
    private LocalTime horario       = LocalTime.now();

    private ModelMapper mapper = new ModelMapper();

    public InvestimentoUseCase(InvestimentoOutbountPort investimentoOutbountPort) {
        this.investimentoOutbountPort = investimentoOutbountPort;
    }

    @Override
    public InvestimentoEntity realizaInvestimento(InvestimentoDto investimentoDto) throws Exception {
        Investimento investimento = mapper.map(investimentoDto, Investimento.class);
        executaRegrasDeNegocio(investimento);
        return investimentoOutbountPort.cadastraInvestimento(investimento);
    }

    public boolean validaValorDoInvestimento(BigDecimal valorInvestimento) {
        return !valorInvestimento.equals(BigDecimal.ZERO);
    }

    public boolean isHorarioDeInvestimento() {
        if (horario.isAfter(horaInicial) && horario.isBefore(horaFinal))
            return false;
        return true;
    }

    public void executaRegrasDeNegocio(Investimento investimento) throws Exception {
        if (isHorarioDeInvestimento())
            throw new Exception("Bolsa fechada.");

        if (!validaValorDoInvestimento(investimento.getValor()))
            throw new Exception("Valor de investimento inválido.");
    }
}
