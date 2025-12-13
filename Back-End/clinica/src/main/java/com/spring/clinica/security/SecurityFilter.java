package com.spring.clinica.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.clinica.entity.User;
import com.spring.clinica.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    // Essa classe filtra, verifica se o usuário está autenticado, rodado a cada requisição
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    //Sobreescreve o método doFilterInternal que é o método de Filtro
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var token = this.recoverToken(request);
        // Validar o token que veio na requisição
        var login = tokenService.validateToken(token);

        //Verifica se não é nulo
        if(login != null){
            //Busca o usuário no banco de dados
            User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuário Não encontrado!"));
            // Cria uma coleção de roles que o user tem
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    // recebe a requisição do usuário
       private String recoverToken(HttpServletRequest request){
        // captura o header da requisição
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        // Substitui o Bearer para uma String vazia
        return authHeader.replace("Bearer ", "");
    }
}
