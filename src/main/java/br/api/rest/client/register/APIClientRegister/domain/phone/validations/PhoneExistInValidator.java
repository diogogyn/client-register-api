package br.api.rest.client.register.APIClientRegister.domain.phone.validations;

import br.api.rest.client.register.APIClientRegister.domain.phone.repository.PhoneRepository;
import br.api.rest.client.register.APIClientRegister.domain.user.Phone;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneExistInValidator implements PhoneValidation {
    @Autowired
    private PhoneRepository phoneRepository;
    @Override
    public void validate(UserRegisterRecord record) {
        List<Phone> allByPhoneIn = this.phoneRepository.findAllByPhoneIn(record.phones());
        if(!allByPhoneIn.isEmpty()){
            throw new ValidationException("Os seguintes telefones informados já estão cadastrdados: "+ getOnlyPhones(allByPhoneIn));
        }
    }

    @Override
    public void validate(UserUpdateRecord record, User user) {

        List<String> newPhones = record.phones();
        List<String> currentPhones = user.getPhones().stream()
                .map(Phone::getPhone)
                .toList();
        List<String> phonesToAdd = newPhones.stream()
                .filter(phone -> !currentPhones.contains(phone))
                .toList();

        List<Phone> allByPhoneInAndUserNot = this.phoneRepository.findAllByPhoneIn(phonesToAdd);
        if(!allByPhoneInAndUserNot.isEmpty())
            throw new ValidationException("Os seguintes telefones informados já estão cadastrdados com outros usuarios: "+ phonesToAdd);
    }

    private List<String> getOnlyPhones(List<Phone> allByPhone){
        List<String> phones = new ArrayList<>();
        for(Phone p : allByPhone){
            phones.add(p.getPhone());
        }
        return phones;
    }
}
