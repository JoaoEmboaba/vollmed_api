package com.example.vollmed.vollmed.entities.medico.DTOs;

import com.example.vollmed.vollmed.entities.medico.Especialidade;
import com.example.vollmed.vollmed.entities.medico.Medico;

public record DTOListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DTOListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
