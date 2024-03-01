package br.api.rest.client.register.APIClientRegister.controller;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.address.Address;
import br.api.rest.client.register.APIClientRegister.domain.user.record.AddressRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserDetailsRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserRegisterRecord> userRegisterRecordJacksonTester;

    @Autowired
    private JacksonTester<UserDetailsRecord> userDetailsRecordJacksonTester;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/user"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        List<String> phones = getPhones(2);
        AddressRecord addressRecord = getAddressRecord();
        UserRegisterRecord userRegister = getUser(addressRecord, phones);
        User user = new User(userRegister);
        when(userRepository.save(any())).thenReturn(user);
        var response = mvc
                .perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegisterRecordJacksonTester.write(userRegister).getJson()))
                .andReturn().getResponse();

        UserDetailsRecord userDetailsRecord = new UserDetailsRecord(user);
        var jsonEsperado = userDetailsRecordJacksonTester.write(userDetailsRecord).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private UserRegisterRecord getUser(AddressRecord address, List<String> phones){

        return new UserRegisterRecord("Usuario da silva 1", "000.000.000-00", address, phones);
    }
    private Address getAddress(){
        return new Address("Rua sem saida", "Bairro triste");
    }
    private AddressRecord getAddressRecord(){
        return new AddressRecord("Rua sem saida", "Bairro triste");
    }
    private List<String> getPhones(int qtdePhones){
        List<String> phones = new ArrayList<>();
        for(int i=0;i<qtdePhones;i++){
            phones.add("123456789"+i);
        }
        return phones;
    }
}