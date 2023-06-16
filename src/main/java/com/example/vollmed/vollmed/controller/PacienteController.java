package com.example.vollmed.vollmed.controller;

import com.example.vollmed.vollmed.entities.paciente.DTOs.DTOAtualizacaoPaciente;
import com.example.vollmed.vollmed.entities.paciente.DTOs.DTOListagemPaciente;
import com.example.vollmed.vollmed.entities.paciente.DTOs.DTOPaciente;
import com.example.vollmed.vollmed.entities.paciente.Paciente;
import com.example.vollmed.vollmed.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DTOPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTOListagemPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListagemPaciente>> listar(@PageableDefault(sort = {"id"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DTOListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListagemPaciente> listar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DTOListagemPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListagemPaciente> atualizar(@RequestBody @Valid DTOAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizar(dados);

        return ResponseEntity.ok(new DTOListagemPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }
}
