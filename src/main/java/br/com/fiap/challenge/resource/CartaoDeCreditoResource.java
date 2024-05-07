package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.CartaoDeCreditoRequest;
import br.com.fiap.challenge.dto.response.CartaoDeCreditoResponse;
import br.com.fiap.challenge.entity.CartaoDeCredito;
import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.service.CartaoDeCreditoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/cartaoDeCredito")
public class CartaoDeCreditoResource implements ResourceDTO<CartaoDeCredito, CartaoDeCreditoRequest, CartaoDeCreditoResponse> {

    @Autowired
    private CartaoDeCreditoService service;

    @GetMapping
    public ResponseEntity<Collection<CartaoDeCreditoResponse>> findAll(
            @RequestParam(name = "numero", required = false) String numero,
            @RequestParam(name = "codSeguranca", required = false) String codSeguranca,
            @RequestParam(name = "bandeira", required = false) String bandeira,
            @RequestParam(name = "limite", required = false) Double limite,
            @RequestParam(name = "validade", required = false)LocalDate validade,
            @RequestParam(name = "fatura", required = false) Double fatura,
            @RequestParam(name = "cliente.nome", required = false) String clienteNome,
            @RequestParam(name = "cliente.cpf",required = false) String clienteCpf,
            @RequestParam(name = "cliente.nascimento", required = false) LocalDate clienteNascimento,
            @RequestParam(name = "cliente.renda", required = false) Double clientRenda
            ){
        var cliente = Cliente.builder()
                .nome(clienteNome)
                .cpf(clienteCpf)
                .nascimento(clienteNascimento)
                .renda(clientRenda)
                .build();

        var cartao = CartaoDeCredito.builder()
                .numero(numero)
                .codSeguranca(codSeguranca)
                .bandeira(bandeira)
                .limite(limite)
                .validade(validade)
                .fatura(fatura)
                .cliente(cliente)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<CartaoDeCredito> example = Example.of(cartao, matcher);
        Collection<CartaoDeCredito> cartoes = service.findAll(example);

        if (Objects.isNull(cartoes))
            return ResponseEntity.notFound().build();

        var response = cartoes.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartaoDeCreditoResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();

        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartaoDeCreditoResponse> save(@RequestBody @Valid CartaoDeCreditoRequest cartaoDeCredito){
        var entity = service.toEntity(cartaoDeCredito);
        var saved = service.save(entity);
        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
