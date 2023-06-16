package com.example.vollmed.vollmed.entities.paciente.DTOs;

import com.example.vollmed.vollmed.entities.endereco.DTOs.DTOEndereco;
import com.example.vollmed.vollmed.entities.endereco.Endereco;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOListagemMedico;
import com.example.vollmed.vollmed.entities.paciente.Paciente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.boot.archive.scan.spi.PackageInfoArchiveEntryHandler;

public record DTOListagemPaciente(@NotNull Long id, @NotBlank String nome, @NotBlank @Email String email,
                                  @NotBlank String telefone, @NotBlank String cpf, @NotBlank Boolean ativo,
                                  @NotBlank @Valid Endereco endereco) {

    public DTOListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),
                paciente.getCpf(), paciente.getAtivo(), paciente.getEndereco());
    }
}
