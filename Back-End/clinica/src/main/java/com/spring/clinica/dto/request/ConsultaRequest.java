package com.spring.clinica.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import com.spring.clinica.entity.Exame;
import com.spring.clinica.entity.Medico;
import com.spring.clinica.entity.Paciente;
import com.spring.clinica.entity.enums.StatusConsulta;

public record ConsultaRequest(
        LocalDateTime data,
        String descricao,
        StatusConsulta statusConsulta,
        Medico medico,
        Paciente paciente,
        List<Exame> exames) {

}
