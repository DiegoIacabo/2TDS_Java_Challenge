package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.AgenciaRequest;
import br.com.fiap.challenge.dto.response.AgenciaResponse;
import br.com.fiap.challenge.entity.Agencia;
import br.com.fiap.challenge.entity.Banco;
import br.com.fiap.challenge.service.AgenciaService;
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
@RequestMapping(value = "/agencia")
public class AgenciaResource implements ResourceDTO<Agencia, AgenciaRequest, AgenciaResponse> {

    @Autowired
    private AgenciaService service;

    @GetMapping
    public ResponseEntity<Collection<AgenciaResponse>> findAll(
            @RequestParam(name = "numero", required = false) String numero,
            @RequestParam(name = "banco.nome", required = false) String bancoNome,
            @RequestParam(name = "cnpj", required = false) String bancoCnpj
    ){
        var banco = Banco.builder()
                .nome(bancoNome)
                .cnpj(bancoCnpj)
                .build();

        var agencia = Agencia.builder()
                .numero(numero)
                .banco(banco)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Agencia> example = Example.of(agencia, matcher);
        Collection<Agencia> agencias = service.findAll(example);

        if (Objects.isNull(agencias))
            return ResponseEntity.notFound().build();

        var response = agencias.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AgenciaResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();

        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AgenciaResponse> save(@RequestBody @Valid AgenciaRequest agencia){
        var entity = service.toEntity(agencia);
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
