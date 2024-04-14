package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteRequest(

        @NotNull(message = "O nome é um campo obrigatório.")
        String nome,

        @NotNull(message = "O CPF deve ser informado.")
        String cpf,

        @NotNull(message = "A renda deve ser informada.")
        Double renda,

        @NotNull(message = "A data de nascimento deve ser informada")
        LocalDate nascimento
) {
}
