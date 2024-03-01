package br.api.rest.client.register.APIClientRegister.domain.user.repository;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);
    long countByCpfAndIdNot(String cpf, Long id);
}
