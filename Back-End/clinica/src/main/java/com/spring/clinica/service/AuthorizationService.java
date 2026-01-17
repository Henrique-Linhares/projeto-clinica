package com.spring.clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.clinica.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // COnsultar os usuários na tabela
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }



}
