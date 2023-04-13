package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Authority {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
@ManyToMany
@JoinTable(
        name = "role",//la table de jointure s'appelle role
        joinColumns = @JoinColumn(name = "id_authority"),//la clé primaire de authority
        inverseJoinColumns =@JoinColumn(name = "email")//car email est la clé primaire de la table User
)
    private List<User> users;
}
