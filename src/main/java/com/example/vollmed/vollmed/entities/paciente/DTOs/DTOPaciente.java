package com.example.vollmed.vollmed.entities.paciente.DTOs;

import com.example.vollmed.vollmed.entities.endereco.DTOs.DTOEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTOPaciente(@NotBlank String nome, @NotBlank @Email String email,
                          @NotBlank @Pattern(regexp = "\\d{11}") String telefone,
                          @NotBlank @Pattern(regexp = "\\d{11}") String cpf, DTOEndereco endereco) {
}
