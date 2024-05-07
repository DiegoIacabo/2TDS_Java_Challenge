package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.ContaRequest;
import br.com.fiap.challenge.dto.response.ClienteResponse;
import br.com.fiap.challenge.dto.response.ContaResponse;
import br.com.fiap.challenge.entity.Agencia;
import br.com.fiap.challenge.entity.Banco;
import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.entity.Conta;
import br.com.fiap.challenge.service.ClienteService;
import br.com.fiap.challenge.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/conta")
public class ContaResource implements ResourceDTO<Conta, ContaRequest, ContaResponse> {

    @Autowired
    private ContaService service;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Collection<ContaResponse>> findAll(
            @RequestParam(name = "saldo", required = false) Double saldo,
            @RequestParam(name = "numero", required = false) String numero,
            @RequestParam(name = "dataAbertura", required = false) LocalDateTime dataAbertura,
            @RequestParam(name = "dataEncerramento", required = false) LocalDateTime dataEncerramento,
            @RequestParam(name = "banco.nome", required = false) String bancoNome,
            @RequestParam(name = "banco.cnpj", required = false) String bancoCnpj,
            @RequestParam(name = "agencia.numero", required = false) String agenciaNumero,
            @RequestParam(name = "cliente.nome", required = false) String clienteNome,
            @RequestParam(name = "cliente.cpf",required = false) String clienteCpf,
            @RequestParam(name = "cliente.nascimento", required = false) LocalDate clienteNascimento,
            @RequestParam(name = "cliente.renda", required = false) Double clienteRenda
            ){
        var banco = Banco.builder()
                .nome(bancoNome)
                .cnpj(bancoCnpj)
                .build();

        var agencia = Agencia.builder()
                .numero(agenciaNumero)
                .banco(banco)
                .build();

        var cliente = Cliente.builder()
                .nome(clienteNome)
                .cpf(clienteCpf)
                .nascimento(clienteNascimento)
                .renda(clienteRenda)
                .build();

        Set<Cliente> collection = new LinkedHashSet<>();
        collection.add(cliente);

        var conta = Conta.builder()
                .saldo(saldo)
                .numero(numero)
                .dataAbertura(dataAbertura)
                .dataEncerramento(dataEncerramento)
                .agencia(agencia)
                .clientes(collection)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Conta> example = Example.of(conta, matcher);
        Collection<Conta> contas = service.findAll(example);

        if (Objects.isNull(contas))
            return ResponseEntity.notFound().build();

        var response = contas.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();

        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaResponse> save(@RequestBody @Valid ContaRequest conta){
        var entity = service.toEntity(conta);
        var saved = service.save(entity);
        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping(value = "/{id}/clientes")
    @Transactional
    public ResponseEntity<ClienteResponse> save(@PathVariable Long id, @RequestBody @Valid AbstractRequest cliente){
        if(Objects.isNull(cliente))
            return ResponseEntity.badRequest().build();

        var conta = service.findById(id);
        if (Objects.isNull(conta))
            return ResponseEntity.notFound().build();

        Cliente clienteEntity = null;
        if (Objects.nonNull(cliente.id())){
            clienteEntity = clienteService.findById(cliente.id());
        }
        conta.getClientes().add(clienteEntity);

        var saved = clienteService.save(clienteEntity);
        var response = clienteService.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
