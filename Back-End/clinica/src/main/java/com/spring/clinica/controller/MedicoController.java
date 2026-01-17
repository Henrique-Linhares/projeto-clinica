package com.spring.clinica.controller;

import com.spring.clinica.dto.request.MedicoRequest;
import com.spring.clinica.dto.response.MedicoResponse;
import com.spring.clinica.service.MedicoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // Endpoint de POST
    @PostMapping
    public ResponseEntity<MedicoResponse> criarMedico(@RequestBody MedicoRequest medicoRequest) {
        MedicoResponse medicoResponse = medicoService.criarMedico(medicoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponse);
    }

    // Buscar Por id
    @GetMapping("/{id}")
    public MedicoResponse buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id);
    }

    // Lista Todos
    @GetMapping
    public List<MedicoResponse> listarTodos() {
        return medicoService.listarTodos();
    }

    // Atualiza Médico
    @PutMapping("/{id}")
    public MedicoResponse atualizarMedico(@PathVariable Long id, @RequestBody MedicoRequest request) {
        return medicoService.atualizar(id, request);
    }

    // Deleta Médico
    @DeleteMapping("/{id}")
    public void deletarMedico(@PathVariable Long id) {
        medicoService.deletar(id);
    }

    @GetMapping("/test-token")
    public ResponseEntity<String> testToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("Token válido! Usuário: " + authentication.getName() +
                    " - Roles: " + authentication.getAuthorities());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
    }
}