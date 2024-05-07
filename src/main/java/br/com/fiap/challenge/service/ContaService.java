package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.ContaRequest;
import br.com.fiap.challenge.dto.response.ContaResponse;
import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.entity.Conta;
import br.com.fiap.challenge.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ContaService implements ServiceDTO<Conta, ContaRequest, ContaResponse> {

    @Autowired
    private ContaRepository repo;

    @Autowired
    private AgenciaService agenciaService;

    @Autowired
    private ClienteService clienteService;

    @Override
    public Conta toEntity(ContaRequest contaRequest) {

        var agencia = agenciaService.findById(contaRequest.agencia().id());

        var clientes = clienteService.findById(contaRequest.clientes().id());

        Set<Cliente> collection = new LinkedHashSet<>();
        collection.add(clientes);

        //clienteService.toResponse(collection);

        return Conta.builder()
                .numero(contaRequest.numero())
                .saldo(contaRequest.saldo())
                .agencia(agencia)
                .clientes(collection)
                .build();

    }

    @Override
    public ContaResponse toResponse(Conta conta) {

        var agencia = agenciaService.toResponse(conta.getAgencia());

        var clientes = conta.getClientes().stream().map(clienteService::toResponse).toList();

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
    public Collection<Conta> findAll(Example<Conta> example) {
        return repo.findAll(example);
    }

    @Override
    public Conta findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Conta save(Conta conta) {
        return repo.save(conta);
    }
}
