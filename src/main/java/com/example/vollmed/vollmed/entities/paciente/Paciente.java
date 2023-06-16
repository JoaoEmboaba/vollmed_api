package com.example.vollmed.vollmed.entities.paciente;

import com.example.vollmed.vollmed.entities.endereco.Endereco;
import com.example.vollmed.vollmed.entities.paciente.DTOs.DTOAtualizacaoPaciente;
import com.example.vollmed.vollmed.entities.paciente.DTOs.DTOPaciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "paciente")
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome, email, telefone, cpf;
    @Embedded
    Endereco endereco;
    private Boolean ativo;

    public Paciente(DTOPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco((dados.endereco()));
    }

    public void atualizar(DTOAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
