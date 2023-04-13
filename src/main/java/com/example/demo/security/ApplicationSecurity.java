package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    //injection de passwordEncoder
    private final PasswordEncoder passwordEncoder;
    //injection de l'interface
    private final UserDetailsService userDetailsService;


    //cette methode permet de gerer les requetes http qui arrivent vers le serveur
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .csrf().disable()
               .authorizeRequests()
              .antMatchers("index.html").permitAll()
              .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority("student:write")
              .antMatchers("/api/v1/**").hasAnyRole("ADMIN","USER")
              .anyRequest()
              .authenticated()
              .and()
              .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //pour s'authentifier il faut passer par ce provider
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){

           DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
           provider.setPasswordEncoder(passwordEncoder);
           provider.setUserDetailsService(userDetailsService);
           return provider;
    }


    /*
    //verifie l'existence des users
    @Override
    @Bean//cet annotation  permet de ne pas generer le mot de passe donnée par spring security mais d'utiliser les credentials qu'on a defini
    protected UserDetailsService userDetailsService() {

        UserDetails admin= User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("student:read","student:write","ROLE_ADMIN")
                .build();

        UserDetails user= User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authorities("student:read","ROLE_USER")
                .build();

        return new InMemoryUserDetailsManager(
                //ici on indique les users qu'on veut ajouter dans notre base de donnes memoire
                        user,admin
        );
    }*/
}
