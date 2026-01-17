package com.spring.clinica.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.clinica.dto.request.AutheticationDTO;
import com.spring.clinica.dto.request.UserRequest;
import com.spring.clinica.dto.response.LoginResponseDTO;
import com.spring.clinica.entity.User;
import com.spring.clinica.repository.UserRepository;
import com.spring.clinica.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager autheticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository userRepository;

  // Endpoint de login e para gerar o token
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Validated AutheticationDTO data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
    var auth = this.autheticationManager.authenticate(usernamePassword);

    // Faz um cast para o objeto User
    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  // Endpoint de registro
  @PostMapping("/registro")
  public ResponseEntity registro(@RequestBody @Validated UserRequest data) {
    if (this.userRepository.findByLogin(data.login()) != null) {
      return ResponseEntity.badRequest().body("Usuário já existe");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

    User newUser = new User(null, data.login(), encryptedPassword, data.role());

    this.userRepository.save(newUser);

    return ResponseEntity.ok("Usuário cadastrado com sucesso");
  }

}
