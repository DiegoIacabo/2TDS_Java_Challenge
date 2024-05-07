package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.BancoRequest;
import br.com.fiap.challenge.dto.response.BancoResponse;
import br.com.fiap.challenge.entity.Banco;
import br.com.fiap.challenge.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/banco")
public class BancoResource implements ResourceDTO<Banco, BancoRequest, BancoResponse> {

    @Autowired
    private BancoService service;

    @GetMapping
    public ResponseEntity<Collection<BancoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cnpj", required = false) String cnpj
    ){
        var banco = Banco.builder()
                .nome(nome)
                .cnpj(cnpj)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Banco> example = Example.of(banco, matcher);
        Collection<Banco> bancos = service.findAll(example);

        if (Objects.isNull(bancos))
            return ResponseEntity.notFound().build();

        var response = bancos.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BancoResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();

        var response = service.toResponse(entity);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BancoResponse> save(@RequestBody @Valid BancoRequest banco) {
        var entity = service.toEntity(banco);
        var saved = service.save(entity);
        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
