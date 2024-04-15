package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.ClienteRequest;
import br.com.fiap.challenge.dto.response.ClienteResponse;
import br.com.fiap.challenge.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Collection;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public Collection<ClienteResponse> findAll(){
        return service.toResponse(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ClienteResponse findById(@PathVariable Long id){
        return service.toResponse(service.findById(id));
    }

    @PostMapping
    @Transactional
    public ClienteResponse save(@RequestBody @Valid ClienteRequest cliente){
        return service.toResponse(service.save(service.toEntity(cliente)));
    }

}
