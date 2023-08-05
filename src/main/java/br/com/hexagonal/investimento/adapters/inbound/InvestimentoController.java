package br.com.hexagonal.investimento.adapters.inbound;

import br.com.hexagonal.investimento.adapters.outbound.InvestimentoEntity;
import br.com.hexagonal.investimento.application.dto.InvestimentoDto;
import br.com.hexagonal.investimento.application.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/investimento")
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @Autowired
    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    @PostMapping
    public InvestimentoEntity realizaInvestimento(@RequestBody InvestimentoDto investimentoDto) throws Exception {
        return investimentoService.realizaInvestimento(investimentoDto);
    }

    @PostMapping(value = "/cep")
    public InvestimentoEntity realizaInvestimentoComCep(@RequestBody InvestimentoDto investimentoDto) throws Exception {
        return investimentoService.realizaInvestimentoComCep(investimentoDto);
    }
}
