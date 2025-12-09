package com.spring.clinica.entity;

import com.spring.clinica.entity.enums.StatusConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;
    private LocalDateTime data;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusConsulta statusConsulta;

    // Muitas consultas tem um médico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    // Muitas consultas tem um paciente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    // Uma consulta tem muitos exames
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exame> exames = new ArrayList<>();
}
