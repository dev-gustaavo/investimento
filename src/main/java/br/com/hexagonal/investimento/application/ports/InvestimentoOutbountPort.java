package br.com.hexagonal.investimento.application.ports;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.domain.Investimento;

public interface InvestimentoOutbountPort {

    InvestimentoEntity cadastraInvestimento(Investimento investimento);
}
