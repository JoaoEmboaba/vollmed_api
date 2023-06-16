package com.example.vollmed.vollmed.entities.paciente.DTOs;

import com.example.vollmed.vollmed.entities.endereco.DTOs.DTOEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOAtualizacaoPaciente(

        @NotNull Long id, @NotBlank String nome, String email, String telefone, DTOEndereco endereco) {
}
