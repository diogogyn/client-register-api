package br.api.rest.client.register.APIClientRegister.domain.user.record;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PhoneRecord(@NotBlank(message = "Telefone é obrigatório") List<String> phoneNumber) {
}
