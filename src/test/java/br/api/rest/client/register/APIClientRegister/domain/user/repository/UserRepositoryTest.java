package br.api.rest.client.register.APIClientRegister.domain.user.repository;

import br.api.rest.client.register.APIClientRegister.domain.phone.Phone;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.address.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    TestEntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    void existsByCpf() {
        List<Phone> phones = getPhones(1);
        Address address = getAddress();
        User user = getUser(address, phones);
        registerUser(user);
        boolean byCpf = this.userRepository.existsByCpf(user.getCpf());
        assertThat(byCpf).isTrue();
    }

    private void registerUser(User user){
        em.persist(user);
    }

    private User getUser(Address address, List<Phone> phones){
        return new User(null, "Usuario", "000.000.000-00", address, phones);
    }
    private Address getAddress(){
        return new Address("Rua sem saida", "Bairro triste");
    }
    private List<Phone> getPhones(int qtdePhones){
        List<Phone> phones = new ArrayList<>();
        for(int i=0;i<qtdePhones;i++){
            phones.add(new Phone("123456789"+i));
        }
        return phones;
    }
}