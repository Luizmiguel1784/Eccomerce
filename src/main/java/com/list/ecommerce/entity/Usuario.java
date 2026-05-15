package com.list.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import com.list.ecommerce.entity.Role;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String photo;
    @Enumerated(EnumType.STRING)
    private Role roles;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new
            ArrayList<>();
}
