package com.example.vollmed.vollmed.controller;

import com.example.vollmed.vollmed.entities.medico.DTOs.DTOAtualizacaoMedico;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOListagemMedico;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOMedicos;
import com.example.vollmed.vollmed.entities.medico.Medico;
import com.example.vollmed.vollmed.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity Cadastrar(@RequestBody @Valid DTOMedicos dados, UriComponentsBuilder uriComponentsBuilder) {

        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTOListagemMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DTOListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListagemMedico> listar(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DTOListagemMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListagemMedico> atualizar(@RequestBody @Valid DTOAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DTOListagemMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
}
