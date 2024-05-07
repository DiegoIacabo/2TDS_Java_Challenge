package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.ClienteRequest;
import br.com.fiap.challenge.dto.response.ClienteResponse;
import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteService implements ServiceDTO<Cliente, ClienteRequest, ClienteResponse> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public Cliente toEntity(ClienteRequest clienteRequest) {
        return Cliente.builder()
                .nome(clienteRequest.nome())
                .cpf(clienteRequest.cpf())
                .nascimento(clienteRequest.nascimento())
                .renda(clienteRequest.renda())
                .build();
    }

    @Override
    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .nascimento(cliente.getNascimento())
                .renda(cliente.getRenda())
                .build();
    }

    @Override
    public Collection<Cliente> findAll(Example<Cliente> example) {
        return repo.findAll(example);
    }

    @Override
    public Cliente findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repo.save(cliente);
    }
}
