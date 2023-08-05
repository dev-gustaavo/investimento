package br.com.hexagonal.investimento.adapters.outbound;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<InvestimentoEntity, Integer> {
}
