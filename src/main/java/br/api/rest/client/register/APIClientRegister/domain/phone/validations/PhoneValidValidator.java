package br.api.rest.client.register.APIClientRegister.domain.phone.validations;

import br.api.rest.client.register.APIClientRegister.domain.phone.repository.PhoneRepository;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class PhoneValidValidator implements PhoneValidation{
    @Autowired
    private PhoneRepository phoneRepository;
    @Override
    public void validate(UserRegisterRecord record) {
        if(record.phones().isEmpty())
            throw new ValidationException("Vocẽ deve cadastrar ao menos um telefone no cadastro");
        this.isValidPhones(record.phones());
    }

    @Override
    public void validate(UserUpdateRecord record, User user) {
        if(record.phones().isEmpty())
            throw new ValidationException("Vocẽ deve manter ao menos um telefone no cadastro");
        this.isValidPhones(record.phones());
    }

    private void isValidPhones(List<String> phones){
        List<String> phonesInvalid = new ArrayList<>();
        for (String phone : phones) {
            if (!isValidPhone(phone)) {
                phonesInvalid.add(phone);
            }
        }
        if(!phonesInvalid.isEmpty())
            throw new ValidationException("Os seguintes telefones informados são invalidos: " + phonesInvalid);
    }
    private static boolean isValidPhone(String phone) {
        // Verifica se o telefone tem entre 10 e 15 caracteres
        if (phone.length() < 10 || phone.length() > 15)
            return false;
        // Verifica se todos os caracteres são números
        return Pattern.matches("\\d+", phone);
    }

}
