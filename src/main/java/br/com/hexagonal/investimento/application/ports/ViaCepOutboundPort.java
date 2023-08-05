package br.com.hexagonal.investimento.application.ports;

import br.com.hexagonal.investimento.application.dto.ViaCepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "ViaCepApi", name = "ViaCepApi", url = "https://viacep.com.br/ws")
public interface ViaCepOutboundPort {

    @GetMapping(value = "/{cep}/json")
    ResponseEntity<ViaCepDto> consultaCep(@RequestParam String cep);
}
