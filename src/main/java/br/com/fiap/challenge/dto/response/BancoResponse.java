package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record BancoResponse(

        Long id,
        String nome,
        String cnpj
) {
}
