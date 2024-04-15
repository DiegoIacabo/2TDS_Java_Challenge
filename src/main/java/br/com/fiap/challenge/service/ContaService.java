package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.ContaRequest;
import br.com.fiap.challenge.dto.response.ContaResponse;
import br.com.fiap.challenge.entity.Conta;
import br.com.fiap.challenge.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContaService implements ServiceDTO<Conta, ContaRequest, ContaResponse, AbstractRequest> {

    @Autowired
    private ContaRepository repo;

    @Autowired
    private AgenciaService agenciaService;

    @Autowired
    private ClienteService clienteService;

    @Override
    public Conta toEntity(ContaRequest contaRequest) {

        var agencia = agenciaService.findByAbstractRequest(contaRequest.agencia());

        return Conta.builder()
                .numero(contaRequest.numero())
                .agencia(agencia)
                .build();

    }

    @Override
    public ContaResponse toResponse(Conta conta) {

        var agencia = agenciaService.toResponse(conta.getAgencia());

        var clientes = clienteService.toResponse(conta.getClientes());

        return ContaResponse.builder()
                .id(conta.getId())
                .saldo(conta.getSaldo())
                .numero(conta.getNumero())
                .dataAbertura(conta.getDataAbertura())
                .dataEncerramento(conta.getDataEncerramento())
                .agencia(agencia)
                .clientes(clientes)
                .build();
    }

    @Override
    public Collection<ContaResponse> toResponse(Collection<Conta> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<Conta> findAll() {
        return repo.findAll();
    }

    @Override
    public Conta findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Conta findByAbstractRequest(AbstractRequest a) {
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Conta save(Conta conta) {
        return repo.save(conta);
    }
}
