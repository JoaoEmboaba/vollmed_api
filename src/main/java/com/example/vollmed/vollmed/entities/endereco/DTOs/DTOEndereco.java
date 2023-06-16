package com.example.vollmed.vollmed.entities.endereco.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOEndereco(

        @NotBlank(message = "O Logradouro não pode estar vazio") String logradouro,
        @NotBlank(message = "O bairro não pode estar vazio") String bairro,
        @NotBlank(message = "O CEP não pode estar vazio e precisa conter 8 números") @Pattern(regexp = "\\d{8}") String cep,
        @NotBlank(message = "O campo de cidade não pode estar vazio") String cidade,
        @NotBlank(message = "O campo do estado não pode estar vazio") String uf,
        String complemento,
        @NotNull(message = "O número não pode estar vazio") Long numero) {

}
