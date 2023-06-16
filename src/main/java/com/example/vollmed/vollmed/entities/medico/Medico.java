package com.example.vollmed.vollmed.entities.medico;

import com.example.vollmed.vollmed.entities.endereco.Endereco;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOAtualizacaoMedico;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOMedicos;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome, email, telefone, crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DTOMedicos dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco((dados.endereco()));
    }

    public void atualizarInformacoes(DTOAtualizacaoMedico dados) {
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
        if(dados.especialidade() != null){
            this.especialidade = dados.especialidade();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
