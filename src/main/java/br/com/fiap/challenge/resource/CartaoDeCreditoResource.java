package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.CartaoDeCreditoRequest;
import br.com.fiap.challenge.dto.response.CartaoDeCreditoResponse;
import br.com.fiap.challenge.service.CartaoDeCreditoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/cartaoDeCredito")
public class CartaoDeCreditoResource {

    @Autowired
    private CartaoDeCreditoService service;

    @GetMapping
    public Collection<CartaoDeCreditoResponse> findAll(){
        return service.toResponse(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public CartaoDeCreditoResponse findById(@PathVariable Long id){
        return service.toResponse(service.findById(id));
    }

    @PostMapping
    @Transactional
    public CartaoDeCreditoResponse save(@RequestBody @Valid CartaoDeCreditoRequest cartaoDeCredito){
        return service.toResponse(service.save(service.toEntity(cartaoDeCredito)));
    }

}
