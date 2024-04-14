package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
}
