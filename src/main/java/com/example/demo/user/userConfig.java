package com.example.demo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

//c'est une classe de configuaration pour ajouter qlq users et qlq authorities comme on a dejà fait avec students
@Configuration
@RequiredArgsConstructor
public class userConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;


  //  @Bean
    CommandLineRunner init_users(){

        return args -> {
User admin=userRepository.save(new User(
        "Admin",
        "admin@gmail.com",
passwordEncoder.encode("admin"),
        true,
        true,
        true,
        true,
        null));
            User user=userRepository.save(new User(
                    "User",
                    "user@gmail.com",
                    passwordEncoder.encode("user"),
                    true,
                    true,
                    true,
                    true,
                    null));

            Authority studentRead=authorityRepository.save(
                    new Authority(null,"student:read",new ArrayList<User>()));
            Authority studentWrite=authorityRepository.save(
                    new Authority(null,"student:write",new ArrayList<User>()));
            Authority studentRoleAdmin=authorityRepository.save(
                    new Authority(null,"ROLE_ADMIN",new ArrayList<User>()));
            Authority studentRoleUser=authorityRepository.save(
                    new Authority(null,"ROLE_USER",new ArrayList<User>()));

            //affectation des authorities aux users
            studentRead.getUsers().add(admin);
            studentRead.getUsers().add(user);
            //mise à jour de studentRead authority
            authorityRepository.save(studentRead);

            //ajout de users aux list des users qui ont l'authority student:write
            studentWrite.getUsers().add(admin);
            //mise à jour de studentRead authority
            authorityRepository.save(studentWrite);

            studentRoleAdmin.getUsers().add(admin);
            authorityRepository.save(studentRoleAdmin);

            studentRoleUser.getUsers().add(user);
            authorityRepository.save(studentRoleUser);

        };
    }
}
