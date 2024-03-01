package br.api.rest.client.register.APIClientRegister.domain.user.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UserUpdateRecord(@NotNull Long id,
                                 String nome,
                                 @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "Formato do CPF é inválido")
                                 String cpf,
                                 AddressRecord address,
                                 List<String> phones) {
}
