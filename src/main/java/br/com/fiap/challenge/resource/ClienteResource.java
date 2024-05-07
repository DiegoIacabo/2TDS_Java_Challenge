package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.ClienteRequest;
import br.com.fiap.challenge.dto.response.ClienteResponse;
import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.service.ClienteService;
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
@RequestMapping(value = "/cliente")
public class ClienteResource implements ResourceDTO<Cliente, ClienteRequest, ClienteResponse> {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Collection<ClienteResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "nascimento", required = false) LocalDate nascimento,
            @RequestParam(name = "renda", required = false) Double renda
            ){
        var cliente = Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .nascimento(nascimento)
                .renda(renda)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Cliente> example = Example.of(cliente, matcher);
        Collection<Cliente> clientes = service.findAll(example);

        if (Objects.isNull(clientes))
            return ResponseEntity.notFound().build();

        var response = clientes.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();

        var response = service.toResponse(entity);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest cliente){
        var entity = service.toEntity(cliente);
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
