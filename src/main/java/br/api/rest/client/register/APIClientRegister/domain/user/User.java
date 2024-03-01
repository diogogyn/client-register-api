package br.api.rest.client.register.APIClientRegister.domain.user;

import br.api.rest.client.register.APIClientRegister.domain.user.address.Address;
import br.api.rest.client.register.APIClientRegister.domain.user.record.PhoneRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "usuario")
@Entity(name = "Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    public User(UserRegisterRecord record) {
        this.setName(record.name());
        this.setCpf(record.cpf());
        this.setAddress(new Address(record.address()));
    }

    public void updateInformation(UserUpdateRecord record) {
        if(record.nome() != null) this.setName(record.nome());
        if(record.cpf() != null) this.setCpf(record.cpf());
        if(record.address() != null) this.address.updateInformation(record.address());
    }

    public void addPhones(List<String> phoneNumber){
        for(String ph: phoneNumber) {
            Phone phone = new Phone(null, this, ph);
            this.phones.add(phone);
        }
    }

    private void updatePhones(List<String> phoneNumber){
        if(!this.phones.isEmpty()) this.phones.clear();

        for(String ph: phoneNumber) {
            Phone phone = new Phone(null, this, ph);
            this.phones.add(phone);
        }
    }



}
