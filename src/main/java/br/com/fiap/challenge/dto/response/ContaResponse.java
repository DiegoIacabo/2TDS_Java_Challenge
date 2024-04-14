package br.com.fiap.challenge.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
public record ContaResponse(

        Long id,
        Double saldo,
        String numero,
        AgenciaResponse agencia,
        Collection<ClienteResponse> clientes,
        LocalDateTime dataAbertura,
        LocalDateTime dataEncerramento
) {
}
