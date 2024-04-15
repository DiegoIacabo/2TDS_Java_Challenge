package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.CartaoDeCreditoRequest;
import br.com.fiap.challenge.dto.response.CartaoDeCreditoResponse;
import br.com.fiap.challenge.entity.CartaoDeCredito;
import br.com.fiap.challenge.repository.CartaoDeCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CartaoDeCreditoService implements ServiceDTO<CartaoDeCredito, CartaoDeCreditoRequest, CartaoDeCreditoResponse, AbstractRequest> {

    @Autowired
    private CartaoDeCreditoRepository repo;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContaService contaService;

    @Override
    public CartaoDeCredito toEntity(CartaoDeCreditoRequest cartaoDeCreditoRequest) {

        var cliente = clienteService.findByAbstractRequest(cartaoDeCreditoRequest.cliente());

        var conta = contaService.findByAbstractRequest(cartaoDeCreditoRequest.conta());

        return CartaoDeCredito.builder()
                .numero(cartaoDeCreditoRequest.numero())
                .codSeguranca(cartaoDeCreditoRequest.codSeguranca())
                .senha(cartaoDeCreditoRequest.senha())
                .validade(cartaoDeCreditoRequest.validade())
                .bandeira(cartaoDeCreditoRequest.bandeira())
                .limite(cartaoDeCreditoRequest.limite())
                .cliente(cliente)
                .conta(conta)
                .build();
    }

    @Override
    public CartaoDeCreditoResponse toResponse(CartaoDeCredito cartaoDeCredito) {

        var cliente = clienteService.toResponse(cartaoDeCredito.getCliente());

        var conta = contaService.toResponse(cartaoDeCredito.getConta());

        return CartaoDeCreditoResponse.builder()
                .id(cartaoDeCredito.getId())
                .numero(cartaoDeCredito.getNumero())
                .codSegurnca(cartaoDeCredito.getCodSeguranca())
                .bandeira(cartaoDeCredito.getBandeira())
                .limite(cartaoDeCredito.getLimite())
                .validade(cartaoDeCredito.getValidade())
                .fatura(cartaoDeCredito.getFatura())
                .cliente(cliente)
                .conta(conta)
                .build();
    }

    @Override
    public Collection<CartaoDeCreditoResponse> toResponse(Collection<CartaoDeCredito> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<CartaoDeCredito> findAll() {
        return repo.findAll();
    }

    @Override
    public CartaoDeCredito findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public CartaoDeCredito findByAbstractRequest(AbstractRequest a) {
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public CartaoDeCredito save(CartaoDeCredito cartaoDeCredito) {
        return repo.save(cartaoDeCredito);
    }
}
