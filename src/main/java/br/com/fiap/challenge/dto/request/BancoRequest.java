package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record BancoRequest(

        @NotNull(message = "O nome é um campo obrigatório.")
        String nome,

        @NotNull(message = "O CNPJ deve ser informado.")
        String cnpj
) {
}
