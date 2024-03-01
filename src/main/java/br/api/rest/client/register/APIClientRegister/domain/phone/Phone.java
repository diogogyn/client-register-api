package br.api.rest.client.register.APIClientRegister.domain.phone;

import br.api.rest.client.register.APIClientRegister.domain.user.User;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User user;
    @Column(name = "telefone")
    private String phone;

    public Phone(String phone) {
        this.phone = phone;
    }
}
