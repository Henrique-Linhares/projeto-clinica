package com.spring.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.clinica.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
