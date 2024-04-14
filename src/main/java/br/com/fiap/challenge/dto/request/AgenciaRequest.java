package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record AgenciaRequest(

        @NotNull(message = "O número da agência é um campo obrigatório.")
        String numero,

        @NotNull(message = "O banco da agência deve ser informado.")
        AbstractRequest banco
) {
}
