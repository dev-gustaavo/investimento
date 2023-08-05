package br.com.hexagonal.investimento.adapters.outbound;

import br.com.hexagonal.investimento.application.ports.InvestimentoOutbountPort;
import br.com.hexagonal.investimento.domain.Investimento;
import br.com.hexagonal.investimento.domain.InvestimentoComCep;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InvestimentoDatastore implements InvestimentoOutbountPort {

    private final InvestimentoRepository investimentoRepository;
    private ModelMapper mapper = new ModelMapper();

    public InvestimentoDatastore(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    @Override
    public InvestimentoEntity cadastraInvestimento(Investimento investimento) {
        InvestimentoEntity investimentoEntity = mapper.map(investimento, InvestimentoEntity.class);
        return investimentoRepository.save(investimentoEntity);
    }

    @Override
    public InvestimentoEntity cadastraInvestimentoComUf(InvestimentoComCep investimentoComCep) {
        InvestimentoEntity investimentoEntity = mapper.map(investimentoComCep, InvestimentoEntity.class);
        return investimentoRepository.save(investimentoEntity);
    }
}
