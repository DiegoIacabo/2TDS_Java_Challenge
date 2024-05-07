package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CartaoDeCreditoRequest(

        @NotNull(message = "O número do cartão é um campo obrigatório.")
        String numero,

        @NotNull(message = "O código de segurança do cartão é um campo obrigatório.")
        String codSeguranca,

        @Size(min = 8, max = 50, message = "Senha inválida.")
        @NotNull(message = "A senha do cartão deve ser informada.")
        String senha,

        @NotNull(message = "A bandeira do cartão deve ser informada.")
        String bandeira,

        @NotNull(message = "A validade do cartão deve ser informada.")
        LocalDate validade,

        @NotNull(message = "O limite do cartão deve ser informado.")
        Double limite,

        //@NotNull(message = "A conta do cartão deve ser informada.")
        //AbstractRequest conta,

        @NotNull(message = "O cliente do cartão deve ser informado.")
        AbstractRequest cliente
) {
}
