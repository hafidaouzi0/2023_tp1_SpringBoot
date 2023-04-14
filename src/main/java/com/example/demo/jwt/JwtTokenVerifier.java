package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter //permet de crrer une instance par filtre{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//on doit verifier si l'utilisateur a envoyé le token
        //pour cela on doit récuperer le header ,car on envoie le token dans le header par un attribut qui s'appelle Authorization
String authorizationHeader = request.getHeader("Authorization");
if(authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer"))
                                              {
          filterChain.doFilter(request,response);   //go to the following filter if the token is not there

              return;
                                              }
//si l'utilisateur vient avec un token on fait cela
try {
    //the secret key we used before
    String secret = "secret092802secret092802secret092802secret092802";

    //on récupère le token en enlevant les premiers Bearer et space
String token=authorizationHeader.substring(7);//Bearer+espace c'est 7 caractères
    //on verifie l'authenticité du token et on utilise la secret key qu'on a dejà utilisé
    //en recuperant aussi l'objet claimsJwt
           Jwt<Header,Claims> claimsJwt= Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
            .build()
            .parseClaimsJwt(token);
           //mainetenant on commence à recuperer les informations pour savoir est ce que le user a les autorisations pour acceder aux ressources
    //on recupere d'abord le subject qui est le username
  String username=  claimsJwt.getBody().getSubject();
  //en récupère les authorisations ,si on teste le token dans le site de jwt on va voir que l'objet authorities est sous forme de tableau d'objets json
    //on le récupère en java
    //c'est un tableau key value , dans java on le récupère sous forme de liste de map
    List<Map<String,String>> authorities = (List<Map<String, String>>) claimsJwt.getBody().get("authorities");
    //authentication attend objet de type grantedAuthority
    Set<GrantedAuthority> grantedAuthorities=authorities.stream().map(
            authority -> new SimpleGrantedAuthority(authority.get("authority"))//on donne le key:"authority" pour obtenir l value
    )                     .collect(Collectors.toSet());
    //pour dire au springsecurity voilà l'utilisateur authentifié , on lui donne l''objet d'utilisateur authentifié
    Authentication authentication=new UsernamePasswordAuthenticationToken(
            username,
            null,//mashi darori n3tiw le password
             grantedAuthorities
    );
    //on dit à spring d'ajouter cet objet authenticate dans spring context ,comme ca spring va savoir que celuila est le user qui est authentifié
    SecurityContextHolder.getContext().setAuthentication(authentication);
    //on dit à spring d'ajouter ce filtre
    filterChain.doFilter(request,response);

}catch (Exception e){
    e.printStackTrace();

}
}
}
