package br.api.rest.client.register.APIClientRegister.domain.user.record;

import br.api.rest.client.register.APIClientRegister.domain.user.User;

public record UserListRecord(Long id,
                                 String name,
                                 String cpf,
                                 String neighborhood) {
    public UserListRecord(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getAddress().getNeighborhood());
    }
}
