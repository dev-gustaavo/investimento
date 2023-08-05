package br.com.hexagonal.investimento.application.dto;

import lombok.Getter;

@Getter
public class ViaCepDto {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
