package com.example.demo.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//un dto
//on va utiliser cette classe pour s'authentifier au lieu d'envoyer bcp d'infos, on envoie un objet contenant username+password
@AllArgsConstructor
@Getter @Setter @NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

}
