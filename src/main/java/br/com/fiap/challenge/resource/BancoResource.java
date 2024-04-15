package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.BancoRequest;
import br.com.fiap.challenge.dto.response.BancoResponse;
import br.com.fiap.challenge.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(value = "/banco")
public class BancoResource {

    @Autowired
    private BancoService service;

    @GetMapping
    public Collection<BancoResponse> findAll(){
        var bancos = service.findAll();
        return service.toResponse(bancos);
    }

    @GetMapping(value = "/{id}")
    public BancoResponse findById(@PathVariable Long id){
        return service.toResponse(service.findById(id));
    }

    @PostMapping
    @Transactional
    public BancoResponse save(@RequestBody @Valid BancoRequest banco) {
        return service.toResponse(service.save(service.toEntity(banco)));
    }

}
