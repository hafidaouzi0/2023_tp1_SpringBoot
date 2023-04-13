package com.example.demo.auth;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//cette classe va implmenter notre interface ApplicationUserDao
//va gerer l'authentification grace aux utilisateur crées dans la memoire
@Repository
public class ApplicationUserRepositoryInMemory implements  ApplicationUserDao{

    private List<ApplicationUserDetails> users;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserRepositoryInMemory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users=List.of(
               new ApplicationUserDetails(
                       passwordEncoder.encode("admin"),
                       "admin",

                               Set.of( "student:read","student:write","ROLE_ADMIN")
                                       .stream().map(authority -> new SimpleGrantedAuthority(authority))//cela equivalent à map(SimpleGrantedAuthority :: new)
                                       .collect(Collectors.toSet()),
                               true,
                               true,
                               true,
                               true),
                new ApplicationUserDetails(
                        passwordEncoder.encode("user"),
                        "user",

                        Set.of( "student:read","ROLE_USER")
                                .stream().map(authority -> new SimpleGrantedAuthority(authority))//cela equivalent à map(SimpleGrantedAuthority :: new)
                                .collect(Collectors.toSet()),
                        true,
                        true,
                        true,
                        true)
        );
    }

    @Override
    public ApplicationUserDetails getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst()
                .orElseThrow(()->new RuntimeException("user not found"));
    }
}
