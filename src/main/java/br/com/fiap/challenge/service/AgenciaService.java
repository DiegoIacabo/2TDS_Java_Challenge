package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.AgenciaRequest;
import br.com.fiap.challenge.dto.response.AgenciaResponse;
import br.com.fiap.challenge.entity.Agencia;
import br.com.fiap.challenge.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AgenciaService implements ServiceDTO<Agencia, AgenciaRequest, AgenciaResponse, AbstractRequest> {

    @Autowired
    private AgenciaRepository repo;

    @Autowired
    private BancoService bancoService;

    @Override
    public Agencia toEntity(AgenciaRequest agenciaRequest) {

        var banco = bancoService.findByAbstractRequest(agenciaRequest.banco());

        return Agencia.builder()
                .numero(agenciaRequest.numero())
                .banco(banco)
                .build();

    }

    @Override
    public AgenciaResponse toResponse(Agencia agencia) {

        var banco = bancoService.toResponse(agencia.getBanco());

        return AgenciaResponse.builder()
                .id(agencia.getId())
                .numero(agencia.getNumero())
                .banco(banco)
                .build();
    }

    @Override
    public Collection<AgenciaResponse> toResponse(Collection<Agencia> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<Agencia> findAll() {
        return repo.findAll();
    }

    @Override
    public Agencia findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Agencia findByAbstractRequest(AbstractRequest a) {
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Agencia save(Agencia agencia) {
        return repo.save(agencia);
    }
}
