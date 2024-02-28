package br.api.rest.client.register.APIClientRegister.domain.user;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "lista_telefones")
@Entity(name = "ListaTelefones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "id_usuario")
    private Long userId;
    @Column(name = "telefone")
    private String phone;
}
