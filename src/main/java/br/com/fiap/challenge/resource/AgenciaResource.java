package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.AgenciaRequest;
import br.com.fiap.challenge.dto.response.AgenciaResponse;
import br.com.fiap.challenge.service.AgenciaService;
import br.com.fiap.challenge.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/agencia")
public class AgenciaResource {

    @Autowired
    private AgenciaService service;

    @GetMapping
    public Collection<AgenciaResponse> findAll(){
        return service.toResponse(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public AgenciaResponse findById(@PathVariable Long id){
        return service.toResponse(service.findById(id));
    }

    @PostMapping
    @Transactional
    public AgenciaResponse save(@RequestBody @Valid AgenciaRequest agencia){
        return service.toResponse(service.save(service.toEntity(agencia)));
    }

}
