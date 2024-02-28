package br.api.rest.client.register.APIClientRegister.domain.user;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "usuario")
@Entity(name = "Usuario")
public class User {
//id, nome, cpf, endereco, bairro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "cpf")
    private String identificationNumber;
    @Column(name = "endereco")
    private String address;
    @Column(name = "bairro")
    private String neighborhood;
}
