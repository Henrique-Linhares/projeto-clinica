package com.spring.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.clinica.entity.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    
}
