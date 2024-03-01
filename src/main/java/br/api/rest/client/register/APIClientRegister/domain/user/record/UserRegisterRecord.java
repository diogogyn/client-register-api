package br.api.rest.client.register.APIClientRegister.domain.user.record;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record UserRegisterRecord(@NotBlank(message = "Nome é obrigatório")
                                 @Length(min = 10, message = "O nome deve ter ao menos 10 caracteres")
                                 String name,
                                 @NotBlank
                                 @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "Formato do CPF é inválido")
                                 String cpf,
                                 @NotNull
                                 @Valid
                                 AddressRecord address,
                                 @NotNull
                                 List<String> phones) {
}
