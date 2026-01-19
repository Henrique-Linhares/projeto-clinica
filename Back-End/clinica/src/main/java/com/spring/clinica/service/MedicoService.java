package com.spring.clinica.service;

import com.spring.clinica.dto.request.MedicoRequest;
import com.spring.clinica.dto.response.MedicoResponse;
import com.spring.clinica.entity.Medico;
import com.spring.clinica.entity.User;
import com.spring.clinica.repository.MedicoRepository;
import com.spring.clinica.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    public MedicoResponse criarMedico(MedicoRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogado = (User) authentication.getPrincipal();


        Medico medico = new Medico();
        medico.setEspecialidade(request.especialidade());
        medico.setUser(userLogado);

        Medico novoMedico = medicoRepository.save(medico);

        return new MedicoResponse(
                novoMedico.getId(),
                novoMedico.getUser().getLogin(),
                novoMedico.getEspecialidade(),
                List.of()
        );
    }

    public MedicoResponse buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + id));

        return new MedicoResponse(
                medico.getId(),
                medico.getUser().getLogin(),
                medico.getEspecialidade(),
                List.of()
        );
    }

    public List<MedicoResponse> listarTodos() {
        return medicoRepository.findAll().stream()
                .map(medico -> new MedicoResponse(
                        medico.getId(),
                        medico.getUser().getLogin(),
                        medico.getEspecialidade(),
                        List.of()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public MedicoResponse atualizar(Long id, MedicoRequest request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + id));

        medico.setEspecialidade(request.especialidade());

        Medico medicoAtualizado = medicoRepository.save(medico);

        return new MedicoResponse(
                medicoAtualizado.getId(),
                medicoAtualizado.getUser().getLogin(),
                medicoAtualizado.getEspecialidade(),
                List.of()
        );
    }

    @Transactional
    public void deletar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado com ID: " + id);
        }
        medicoRepository.deleteById(id);
    }
}