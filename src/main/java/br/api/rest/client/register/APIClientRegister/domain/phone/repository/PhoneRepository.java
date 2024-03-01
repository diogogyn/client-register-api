package br.api.rest.client.register.APIClientRegister.domain.phone.repository;
import br.api.rest.client.register.APIClientRegister.domain.phone.Phone;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllByPhoneIn(List<String> phones);
    List<Phone> findAllByPhoneInAndUserNot(List<String> phoneNumbers, User user);

    Phone findByUserAndPhone(User user, String phoneToDelete);
    List<Phone> findAllByUser(User user);
}
