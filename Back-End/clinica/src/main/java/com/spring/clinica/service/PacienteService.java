package com.spring.clinica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.clinica.dto.request.PacienteRequest;
import com.spring.clinica.dto.response.PacienteResponse;
import com.spring.clinica.entity.Paciente;
import com.spring.clinica.entity.User;
import com.spring.clinica.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public PacienteResponse criaPaciente(PacienteRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogado = (User) authentication.getPrincipal();

        Paciente paciente = new Paciente();
        paciente.setIdade(request.idade());
        paciente.setUser(userLogado); 

        Paciente novoPaciente = pacienteRepository.save(paciente);

        return new PacienteResponse(
                novoPaciente.getId(),
                novoPaciente.getUser().getLogin(),
                novoPaciente.getIdade(),
                List.of());
    }

    public PacienteResponse buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));

        return new PacienteResponse(
                paciente.getId(),
                paciente.getUser() != null ? paciente.getUser().getLogin() : "Sem login associado",
                paciente.getIdade(),
                List.of());
    }

    public List<PacienteResponse> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(paciente -> new PacienteResponse(
                        paciente.getId(),
                        paciente.getUser() != null ? paciente.getUser().getLogin() : "Sem login associado",
                        paciente.getIdade(),
                        List.of()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PacienteResponse atualizar(Long id, PacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));

        paciente.setIdade(request.idade());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);

        return new PacienteResponse(
                pacienteAtualizado.getId(),
                pacienteAtualizado.getUser().getLogin(),
                pacienteAtualizado.getIdade(),
                List.of());
    }

    @Transactional
    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}