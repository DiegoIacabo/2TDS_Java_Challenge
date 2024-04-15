package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.BancoRequest;
import br.com.fiap.challenge.dto.response.BancoResponse;
import br.com.fiap.challenge.entity.Banco;
import br.com.fiap.challenge.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BancoService implements ServiceDTO<Banco, BancoRequest, BancoResponse, AbstractRequest> {

    @Autowired
    private BancoRepository repo;

    @Override
    public Banco toEntity(BancoRequest bancoRequest) {
        return Banco.builder().nome(bancoRequest.nome()).cnpj(bancoRequest.cnpj()).build();
    }

    @Override
    public BancoResponse toResponse(Banco banco) {
        return BancoResponse.builder().id(banco.getId()).nome(banco.getNome()).cnpj(banco.getCnpj()).build();
    }

    @Override
    public Collection<BancoResponse> toResponse(Collection<Banco> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<Banco> findAll() {
        return repo.findAll();
    }

    @Override
    public Banco findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Banco findByAbstractRequest(AbstractRequest a) {
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Banco save(Banco banco) {
        return repo.save(banco);
    }
}
