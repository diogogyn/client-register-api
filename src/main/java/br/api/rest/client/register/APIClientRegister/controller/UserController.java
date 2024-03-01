package br.api.rest.client.register.APIClientRegister.controller;

import br.api.rest.client.register.APIClientRegister.domain.phone.repository.PhoneRepository;
import br.api.rest.client.register.APIClientRegister.domain.user.Phone;
import br.api.rest.client.register.APIClientRegister.domain.user.UserService;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserListRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserUpdateRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.repository.UserRepository;
import br.api.rest.client.register.APIClientRegister.domain.user.User;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserRegisterRecord;
import br.api.rest.client.register.APIClientRegister.domain.user.record.UserDetailsRecord;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity create(@RequestBody @Valid UserRegisterRecord record, UriComponentsBuilder uriBuilder) {
        UserDetailsRecord save = this.userService.save(record);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(save.id()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @GetMapping
    @Cacheable(value = "listAllUsers")
    public ResponseEntity<Page<UserListRecord>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        Page<UserListRecord> pageResult = this.userRepository.findAll(page).map(UserListRecord::new);
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity listOne(@PathVariable Long id){
        User user = this.userRepository.getReferenceById(id);
        return ResponseEntity.ok(new UserDetailsRecord(user));
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity update(@RequestBody @Valid UserUpdateRecord record){
        UserDetailsRecord update = this.userService.update(record);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id){
        this.userService.detele(id);
        User user = this.userRepository.getReferenceById(id);
        this.userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
