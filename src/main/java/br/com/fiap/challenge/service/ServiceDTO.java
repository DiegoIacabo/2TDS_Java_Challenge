package br.com.fiap.challenge.service;

import java.util.Collection;

public interface ServiceDTO<Entity, Request, Response, AbstractRequest>{

    Entity toEntity(Request request);

    Response toResponse(Entity entity);

    Collection<Response> toResponse(Collection<Entity> entity);

    Collection<Entity> findAll();

    Entity findById(Long id);

    Entity findByAbstractRequest(AbstractRequest a);

    Entity save(Entity entity);
}
