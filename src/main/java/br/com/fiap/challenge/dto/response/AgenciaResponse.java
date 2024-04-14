package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record AgenciaResponse(

        Long id,
        String numero,
        BancoResponse banco

) {
}
