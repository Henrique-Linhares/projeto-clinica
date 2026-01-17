package com.spring.clinica.controller;
import com.spring.clinica.dto.request.PacienteRequest;
import com.spring.clinica.dto.response.PacienteResponse;
import com.spring.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> criaPaciente(@RequestBody PacienteRequest request) {
        PacienteResponse pacienteResponse = pacienteService.criaPaciente(request);
        return ResponseEntity.ok().body(pacienteResponse);
    }

    // Buscar Por id
    @GetMapping("/{id}")
    public PacienteResponse buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    // Lista Todos
    @GetMapping
    public List<PacienteResponse> listarTodos() {
        return pacienteService.listarTodos();
    }

    // Atualiza Paciente
    @PutMapping("/{id}")
    public PacienteResponse atualizarPaciente(@PathVariable Long id, @RequestBody PacienteRequest request) {
        return pacienteService.atualizar(id, request);
    }

    // Deleta Médico
    @DeleteMapping("/{id}")
    public void deletarPaciente(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}
