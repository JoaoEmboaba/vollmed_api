package com.example.vollmed.vollmed.entities.medico.DTOs;

import com.example.vollmed.vollmed.entities.endereco.DTOs.DTOEndereco;
import com.example.vollmed.vollmed.entities.medico.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOMedicos(

        @NotBlank(message = "O nome não pode estar vazio!") String nome,
        @NotBlank(message = "O email não pode estar vazio e precisa conter um @") @Email String email,
        @NotBlank(message = "O telefone não pode estar vazio e precisa ter 10 números, contando com o DDD")
        @Pattern(regexp = "\\d{10}") String telefone,
        @NotBlank(message = "O crm não pode estar vazio e tem que ter de 4 à 6 números") @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull(message = "A especialidade não pode estar vazia") Especialidade especialidade,
        @NotNull(message = "O endereço não pode estar vazio") @Valid DTOEndereco endereco) {
}
