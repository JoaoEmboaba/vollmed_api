package com.example.vollmed.vollmed.entities.usuario;

import jakarta.validation.constraints.NotBlank;

public record DTOAutenticacao(

        @NotBlank String login, @NotBlank String senha
) {
}
