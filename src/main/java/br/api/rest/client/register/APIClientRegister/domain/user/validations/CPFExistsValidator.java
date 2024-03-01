package br.api.rest.client.register.APIClientRegister.domain.user.validations;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CPFExistsValidator implements UserValidation {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserRegisterRecord record) {
        if(this.userRepository.existsByCpf(record.cpf()))
            throw new ValidationException("Já existe um usuario com o cpf informado");
    }

    @Override
    public void validate(UserUpdateRecord record) {
        //to be implemented if necessary
    }

    @Override
    public void validate(UserUpdateRecord record, User user) {
        if(this.userRepository.countByCpfAndIdNot(record.cpf(), user.getId()) > 0)
            throw new ValidationException("Este CPF já esta vinculado a outro usuario");
    }
}
