package br.com.hexagonal.investimento.application.service;

import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.domain.Investimento;

public interface InvestimentoService {

    Investimento realizaInvestimento(InvestimentoDto investimentoDto) throws Exception;
}
