package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.CartaoDeCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Long> {
}
