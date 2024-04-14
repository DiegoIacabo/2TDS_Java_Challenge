package br.com.fiap.challenge.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClienteResponse(

        Long id,
        String nome,
        String cpf,
        LocalDate nascimento,
        Double renda
) {
}
