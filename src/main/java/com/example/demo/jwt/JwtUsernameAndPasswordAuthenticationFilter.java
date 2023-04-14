package com.example.demo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //ce premier filtre va verifier est ce que vous etes authentifiés ou non
    //si vous etes un user de systeme va vous generer un token
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest =
                    new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ) ;
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//si tout se passe bien et l'objet authenticate est declenché ,la methode successfulAuthentication sera declenché
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //si le user est authentifié on le créé son token
        //subject dans jwt est le username
        //builder càd to build the token
        //des claims veut dire qu"on est en train d'ajouter des choses dans le payload sous forme de key value
        //issed date c'est la date de démarrage du token

        String secret = "secret092802secret092802secret092802secret092802";
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
//envoi du token au user à travers response header
        response.addHeader("Authorization","Bearer "+token);
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
