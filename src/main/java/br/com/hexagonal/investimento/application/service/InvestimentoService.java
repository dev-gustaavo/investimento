package br.com.hexagonal.investimento.application.service;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.application.dto.InvestimentoDto;

public interface InvestimentoService {

    InvestimentoEntity realizaInvestimento(InvestimentoDto investimentoDto) throws Exception;

    InvestimentoEntity realizaInvestimentoComCep(InvestimentoDto investimentoDto) throws Exception;
}
