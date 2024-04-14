package br.com.fiap.challenge.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CartaoDeCreditoResponse(

        Long id,
        String numero,
        String codSegurnca,
        String bandeira,
        Double limite,
        LocalDate validade,
        Double fatura,
        ClienteResponse cliente,
        ContaResponse conta
) {
}
