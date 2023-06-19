package com.example.vollmed.vollmed.entities.medico.DTOs;

import com.example.vollmed.vollmed.entities.medico.Especialidade;
import com.example.vollmed.vollmed.entities.medico.Medico;

public record DTOListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

    public DTOListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("\nMédico cadastrado { ").append("\nNome: " + nome).append("\nEmail: " + email).append("\nCRM: " + crm).append("\nEspecialidade: " + especialidade + "\n}").toString();
    }
}
