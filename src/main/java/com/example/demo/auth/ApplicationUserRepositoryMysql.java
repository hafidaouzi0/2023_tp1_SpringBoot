package com.example.demo.auth;


import com.example.demo.user.Authority;
import com.example.demo.user.AuthorityRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("mysqlRepo")
@RequiredArgsConstructor
public class ApplicationUserRepositoryMysql implements ApplicationUserDao {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    @Override
    public ApplicationUserDetails getUserByUsername(String username) {

        Optional<User> userFound=userRepository.findById(username);
        if(!userFound.isPresent()){
            throw new RuntimeException("user not found");
        }
        User user=userFound.get();
        //la liste des authorities de ce user
        List<Authority> authorities=authorityRepository.findAuthoritiesByUsersContains(user);
        ApplicationUserDetails applicationUser=new ApplicationUserDetails(
                user.getPassword(),
                user.getEmail(),
                authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
                        .collect(Collectors.toSet()),
                user.isAccountNonExpired(),
                user.isAccountNotLocked(),
                user.isCredentialsNonExpired(),
                user.isEnable()

        );
        return null;
    }
}
