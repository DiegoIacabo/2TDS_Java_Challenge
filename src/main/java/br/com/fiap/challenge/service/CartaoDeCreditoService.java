package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.CartaoDeCreditoRequest;
import br.com.fiap.challenge.dto.response.CartaoDeCreditoResponse;
import br.com.fiap.challenge.entity.CartaoDeCredito;
import br.com.fiap.challenge.repository.CartaoDeCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CartaoDeCreditoService implements ServiceDTO<CartaoDeCredito, CartaoDeCreditoRequest, CartaoDeCreditoResponse> {

    @Autowired
    private CartaoDeCreditoRepository repo;

    @Autowired
    private ClienteService clienteService;

    //@Autowired
    //private ContaService contaService;

    @Override
    public CartaoDeCredito toEntity(CartaoDeCreditoRequest cartaoDeCreditoRequest) {

        var cliente = clienteService.findById(cartaoDeCreditoRequest.cliente().id());

        //var conta = contaService.findById(cartaoDeCreditoRequest.conta().id());

        return CartaoDeCredito.builder()
                .numero(cartaoDeCreditoRequest.numero())
                .codSeguranca(cartaoDeCreditoRequest.codSeguranca())
                .senha(cartaoDeCreditoRequest.senha())
                .validade(cartaoDeCreditoRequest.validade())
                .bandeira(cartaoDeCreditoRequest.bandeira())
                .limite(cartaoDeCreditoRequest.limite())
                .cliente(cliente)
        //        .conta(conta)
                .build();
    }

    @Override
    public CartaoDeCreditoResponse toResponse(CartaoDeCredito cartaoDeCredito) {

        var cliente = clienteService.toResponse(cartaoDeCredito.getCliente());

        //var conta = contaService.toResponse(cartaoDeCredito.getConta());

        return CartaoDeCreditoResponse.builder()
                .id(cartaoDeCredito.getId())
                .numero(cartaoDeCredito.getNumero())
                .codSeguranca(cartaoDeCredito.getCodSeguranca())
                .bandeira(cartaoDeCredito.getBandeira())
                .limite(cartaoDeCredito.getLimite())
                .validade(cartaoDeCredito.getValidade())
                .fatura(cartaoDeCredito.getFatura())
                .cliente(cliente)
        //        .conta(conta)
                .build();
    }

    @Override
    public Collection<CartaoDeCredito> findAll(Example<CartaoDeCredito> example) {
        return repo.findAll(example);
    }

    @Override
    public CartaoDeCredito findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public CartaoDeCredito save(CartaoDeCredito cartaoDeCredito) {
        return repo.save(cartaoDeCredito);
    }
}
