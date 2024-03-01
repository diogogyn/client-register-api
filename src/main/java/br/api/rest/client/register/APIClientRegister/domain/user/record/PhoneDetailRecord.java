package br.api.rest.client.register.APIClientRegister.domain.user.record;

import br.api.rest.client.register.APIClientRegister.domain.user.Phone;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record PhoneDetailRecord(List<String> phoneNumber) {
}
