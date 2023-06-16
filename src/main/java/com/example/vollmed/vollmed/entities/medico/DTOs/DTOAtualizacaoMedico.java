package com.example.vollmed.vollmed.entities.medico.DTOs;

import com.example.vollmed.vollmed.entities.endereco.DTOs.DTOEndereco;
import com.example.vollmed.vollmed.entities.medico.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DTOAtualizacaoMedico(@NotNull(message = "Você precisa informar o ID para atualizar o médico")
                                   Long id, String nome, String email, Especialidade especialidade,
                                   String telefone, DTOEndereco endereco) {
}
