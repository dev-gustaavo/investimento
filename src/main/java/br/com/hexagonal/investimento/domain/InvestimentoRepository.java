package br.com.hexagonal.investimento.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<Investimento, Integer> {
}
