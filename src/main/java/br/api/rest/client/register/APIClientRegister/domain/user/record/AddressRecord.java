package br.api.rest.client.register.APIClientRegister.domain.user.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddressRecord(

        @NotBlank(message = "O Endereço é obrigatório")
        @Length(min = 3, message = "Informe ao menos um endereço com 3 caracteres")
        String address,
        @NotBlank(message = "Bairro é obrigatório")
        String neighborhood

) {
}