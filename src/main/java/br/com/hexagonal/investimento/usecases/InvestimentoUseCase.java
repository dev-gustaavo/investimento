package br.com.hexagonal.investimento.usecases;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.application.dto.ViaCepDto;
import br.com.hexagonal.investimento.application.ports.InvestimentoOutbountPort;
import br.com.hexagonal.investimento.application.ports.ViaCepOutboundPort;
import br.com.hexagonal.investimento.application.service.InvestimentoService;
import br.com.hexagonal.investimento.domain.Investimento;
import br.com.hexagonal.investimento.domain.InvestimentoComCep;
import feign.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
public class InvestimentoUseCase implements InvestimentoService {

    private final InvestimentoOutbountPort investimentoOutbountPort;
    private final ViaCepOutboundPort viaCepOutboundPort;

    private LocalTime horaInicial   = LocalTime.of(8, 0);
    private LocalTime horaFinal     = LocalTime.of(18, 0);
    private LocalTime horario       = LocalTime.now();

    private ModelMapper mapper = new ModelMapper();

    public InvestimentoUseCase(InvestimentoOutbountPort investimentoOutbountPort, ViaCepOutboundPort viaCepOutboundPort) {
        this.investimentoOutbountPort = investimentoOutbountPort;
        this.viaCepOutboundPort = viaCepOutboundPort;
    }

    @Override
    public InvestimentoEntity realizaInvestimento(InvestimentoDto investimentoDto) throws Exception {
        Investimento investimento = mapper.map(investimentoDto, Investimento.class);
        executaRegrasDeNegocio(investimento);
        return investimentoOutbountPort.cadastraInvestimento(investimento);
    }

    @Override
    public InvestimentoEntity realizaInvestimentoComCep(InvestimentoDto investimentoDto) throws Exception {

        InvestimentoComCep investimentoComCep = mapper.map(investimentoDto, InvestimentoComCep.class);

        isHorarioDeInvestimento();
        validaValorDoInvestimento(investimentoComCep.getValor());

        ViaCepDto viaCep = getViaCep(investimentoComCep.getCep());

        investimentoComCep.setUf(viaCep.getUf());

        return investimentoOutbountPort.cadastraInvestimentoComUf(investimentoComCep);
    }

    private ViaCepDto getViaCep(String cep) throws Exception {
        if (cep.isEmpty())
            throw new Exception("Informe o CEP.");

        ResponseEntity<ViaCepDto> response = viaCepOutboundPort.consultaCep(cep);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new Exception("Erro ao consultar o CEP.");

        return mapper.map(response.getBody(), ViaCepDto.class);
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
