package br.api.rest.client.register.APIClientRegister.domain.user.validations;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UserExistsValidator implements UserValidation {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserRegisterRecord record) {
        this.validateCPF(record.cpf());
    }

    @Override
    public void validate(UserUpdateRecord record) {
        if(!this.userRepository.existsById(record.id()))
            throw new ValidationException("Não encontramos o usuario pelo ID informado");
        this.validateCPF(record.cpf());

    }

    @Override
    public void validate(UserUpdateRecord record, User user) {
        //to be implemented if necessary
    }

    private void validateCPF(String cpf){
        if(this.userRepository.existsByCpf(cpf))
            throw new ValidationException("Já existe um usuario com o cpf informado");
    }
}
