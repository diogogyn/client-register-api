package br.api.rest.client.register.APIClientRegister.domain.user.service;

import br.api.rest.client.register.APIClientRegister.domain.phone.Phone;
import br.api.rest.client.register.APIClientRegister.domain.phone.repository.PhoneRepository;
import br.api.rest.client.register.APIClientRegister.domain.phone.validations.PhoneValidation;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserDetailsRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.repository.UserRepository;
import br.api.rest.client.register.APIClientRegister.domain.user.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private List<UserValidation> userValidationList;
    @Autowired
    private List<PhoneValidation> phoneValidationList;
    public UserDetailsRecord save(UserRegisterRecord record){
        this.userValidationList.forEach(v -> v.validate(record));
        this.phoneValidationList.forEach(v -> v.validate(record));

        User user = new User(record);
        user.addPhones(record.phones());

        User save = this.userRepository.save(user);
        return new UserDetailsRecord(save);
    }

    public UserDetailsRecord update(UserUpdateRecord record){
        //1111,2222,3333,4444
        //2222,4444,5555
        //encontrar todos os telefones que não pertencem a este usuario
        this.userValidationList.forEach(v -> v.validate(record));
        User user = this.userRepository.findById(record.id()).get();
        this.userValidationList.forEach(v -> v.validate(record, user));
        this.phoneValidationList.forEach(v -> v.validate(record, user));
        user.updateInformation(record);
        this.updatePhones(record.phones(), user);
        return new UserDetailsRecord(user);
    }

    public void detele(Long id){
        User user = this.userRepository.getReferenceById(id);
        List<Phone> allByUser = this.phoneRepository.findAllByUser(user);
        this.phoneRepository.deleteAll(allByUser);
        this.userRepository.delete(user);
    }

    private void updatePhones(List<String> newPhones, User user){
        List<String> currentPhones = user.getPhones().stream()
                .map(Phone::getPhone)
                .toList();
        List<String> phonesToAdd = newPhones.stream()
                .filter(phone -> !currentPhones.contains(phone))
                .toList();
        List<String> phonesToDelete = currentPhones.stream()
                .filter(phone -> !newPhones.contains(phone))
                .toList();

        this.deletePhones(phonesToDelete, user);
        this.addPhones(phonesToAdd,user);

    }

    private void deletePhones(List<String> phonesToDelete, User user){
        for (String phoneToDelete : phonesToDelete) {
            Phone phone = phoneRepository.findByUserAndPhone(user, phoneToDelete);
            if (phone != null) {
                phoneRepository.delete(phone);
            }
        }
    }
    private void addPhones(List<String> phonesToAdd, User user){
        for (String phoneToAdd : phonesToAdd) {
            Phone newPhone = new Phone(null, user, phoneToAdd);
            phoneRepository.save(newPhone);
        }
    }
}
