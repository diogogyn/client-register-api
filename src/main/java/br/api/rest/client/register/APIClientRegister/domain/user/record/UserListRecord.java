package br.api.rest.client.register.APIClientRegister.domain.user.record;

import br.api.rest.client.register.APIClientRegister.domain.user.Phone;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.address.Address;

import java.util.List;

public record UserListRecord(Long id,
                                 String name,
                                 String cpf,
                                 String neighborhood) {
    public UserListRecord(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getAddress().getNeighborhood());
    }
}
