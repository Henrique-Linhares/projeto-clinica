package com.spring.clinica.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import com.spring.clinica.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // @Value e ${} mostra que o valor vem do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    // Método para gerar o token
    public String generateToken(User user) {
        try {
            // Cria a chave privada para autenticação
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // Criação do Token
            String token = JWT.create()
                    // Nome do microserviço
                    .withIssuer("clinica")
                    // Adiciona o email do usuário no token
                    .withSubject(user.getEmail())
                    // Coloca um tempo de expiração para o token
                    .withExpiresAt(this.generateExpirationDate())
                    // gera o token
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na autenticação.");
        }
    }

    // Método para validar o token
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("clinica")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    // Função para criar o tempode expiração do token
    private Instant generateExpirationDate() {
        // O tempo de agora + 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
