package br.com.hexagonal.investimento.usecases;

import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.application.service.InvestimentoService;
import br.com.hexagonal.investimento.domain.Investimento;
import br.com.hexagonal.investimento.domain.InvestimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
public class InvestimentoUseCase implements InvestimentoService {

    private final InvestimentoRepository investimentoRepository;

    private LocalTime horaInicial   = LocalTime.of(8, 0);
    private LocalTime horaFinal     = LocalTime.of(18, 0);
    private LocalTime horario       = LocalTime.now();

    private ModelMapper mapper = new ModelMapper();

    public InvestimentoUseCase(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    @Override
    public Investimento realizaInvestimento(InvestimentoDto investimentoDto) throws Exception {
        executaRegrasDeNegocio(investimentoDto);
        Investimento investimento = mapper.map(investimentoDto, Investimento.class);
        return investimentoRepository.save(investimento);
    }

    public boolean validaValorDoInvestimento(BigDecimal valorInvestimento) {
        return !valorInvestimento.equals(BigDecimal.ZERO);
    }

    public boolean isHorarioDeInvestimento() {
        if (horario.isAfter(horaInicial) && horario.isBefore(horaFinal))
            return false;
        return true;
    }

    public void executaRegrasDeNegocio(InvestimentoDto investimentoDto) throws Exception {
        if (isHorarioDeInvestimento())
            throw new Exception("Bolsa fechada.");

        if (!validaValorDoInvestimento(investimentoDto.getValor()))
            throw new Exception("Valor de investimento inválido.");
    }
}
