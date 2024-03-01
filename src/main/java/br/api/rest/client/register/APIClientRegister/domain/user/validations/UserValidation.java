package br.api.rest.client.register.APIClientRegister.domain.user.validations;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;

public interface UserValidation {

    void validate(UserRegisterRecord record);
    void validate(UserUpdateRecord record);
    void validate(UserUpdateRecord record, User user);

}
