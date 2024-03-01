package br.api.rest.client.register.APIClientRegister.domain.phone.validations;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;

import java.util.List;

public interface PhoneValidation {

//    void validateRegister(UserRegisterRecord record);

    void validate(UserRegisterRecord record);
    void validate(UserUpdateRecord record, User user);

}
