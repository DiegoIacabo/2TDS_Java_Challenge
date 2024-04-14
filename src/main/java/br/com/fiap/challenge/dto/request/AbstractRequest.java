package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record AbstractRequest(

        @NotNull(message = "O ID deve ser informado.")
        Long id
) {
}
