package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.AbstractRequest;
import br.com.fiap.challenge.dto.request.ContaRequest;
import br.com.fiap.challenge.dto.response.ContaResponse;
import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.service.ClienteService;
import br.com.fiap.challenge.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/conta")
public class ContaResource {

    @Autowired
    private ContaService service;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Collection<ContaResponse> findAll(){
        return service.toResponse(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ContaResponse findById(@PathVariable Long id){
        return service.toResponse(service.findById(id));
    }

    @PostMapping
    @Transactional
    public ContaResponse save(@RequestBody @Valid ContaRequest conta){
        return service.toResponse(service.save(service.toEntity(conta)));
    }

    @PostMapping(value = "/{id}/clientes")
    @Transactional
    public ContaResponse save(@PathVariable Long id, @RequestBody @Valid AbstractRequest cliente){
        if(Objects.isNull(cliente)) return null;
        var conta = service.findById(id);

        Cliente clienteEntity = null;
        if (Objects.nonNull(cliente.id())){
            clienteEntity = clienteService.findById(cliente.id());
        }
        conta.getClientes().add(clienteEntity);

        return service.toResponse(conta);
    }

}
