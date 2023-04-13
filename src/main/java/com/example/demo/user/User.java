package com.example.demo.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    private String name;

    @Id @Column(length = 30)//on precise la length puisque le id est une chaine de caractere
    private String email;
    private String password;

    private boolean accountNonExpired;
    private boolean accountNotLocked;
    private boolean credentialsNonExpired;
    private boolean enable;
    //list des autorities

    @ManyToMany
    private List<Authority> authorities;


}
