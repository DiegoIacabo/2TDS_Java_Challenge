package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
}
