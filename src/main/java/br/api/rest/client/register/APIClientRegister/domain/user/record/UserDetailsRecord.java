package br.api.rest.client.register.APIClientRegister.domain.user.record;

import br.api.rest.client.register.APIClientRegister.domain.phone.Phone;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.address.Address;

import java.util.ArrayList;
import java.util.List;

public record UserDetailsRecord(Long id,
                                 String name,
                                 String cpf,
                                 Address address,
                                 List<String> phones) {
    public UserDetailsRecord(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getAddress(), convertToPhoneDetailRecords(user.getPhones()));
    }
    private static List<String> convertToPhoneDetailRecords(List<Phone> phones) {
        List<String> phoneNumber = new ArrayList<>();
        for(Phone phone :phones){
            phoneNumber.add(phone.getPhone());
        }
        return phoneNumber;
    }
}
