package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record ContaRequest(

        @NotNull(message = "O número da conta é um campo obrigatório.")
        String numero,

        @NotNull(message = "A agência da conta deve ser informada.")
        AbstractRequest agencia,

        @NotNull(message = "O saldo precisa ser inserido.")
        Double saldo,

        @NotNull(message = "O(s) cliente(s) da conta deve(m) ser informado(s).")
        AbstractRequest clientes
) {
}
