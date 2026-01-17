package com.spring.clinica.repository;

import com.spring.clinica.entity.Medico;
import com.spring.clinica.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    List<Medico> findByEspecialidade(String especialidade);
    
    Optional<Medico> findByUser(User user);
    
    boolean existsByUser(User user);
}