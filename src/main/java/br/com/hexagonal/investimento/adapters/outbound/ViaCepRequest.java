package br.com.hexagonal.investimento.adapters.outbound;

import br.com.hexagonal.investimento.application.dto.ViaCepDto;
import br.com.hexagonal.investimento.application.ports.ViaCepOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ViaCepRequest implements ViaCepOutboundPort {

    @Autowired
    ViaCepOutboundPort viaCepOutboundPort;
    
    @Override
    public ResponseEntity<ViaCepDto> consultaCep(String cep) {
        return viaCepOutboundPort.consultaCep(cep);
    }
}
