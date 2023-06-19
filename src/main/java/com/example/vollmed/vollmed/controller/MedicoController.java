package com.example.vollmed.vollmed.controller;

import com.example.vollmed.vollmed.entities.medico.DTOs.DTOAtualizacaoMedico;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOListagemMedico;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOMedicos;
import com.example.vollmed.vollmed.entities.medico.Medico;
import com.example.vollmed.vollmed.repository.MedicoRepository;
import com.example.vollmed.vollmed.service.MedicoService;
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
import java.io.Serializable;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<String> Cadastrar(@RequestBody DTOMedicos dados, UriComponentsBuilder uriComponentsBuilder) {

        var medico = service.cadastrarMedico(new Medico(dados));
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body("Usuário criado com sucesso!! \n" + new DTOListagemMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        return ResponseEntity.ok(service.listarMedicos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListagemMedico> listar(@PathVariable Long id) {
        return ResponseEntity.ok(new DTOListagemMedico(service.buscarMedicoPorId(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListagemMedico> atualizar(@RequestBody @Valid DTOAtualizacaoMedico dados) {
        return ResponseEntity.ok(new DTOListagemMedico(service.atualizarMedicoPorId(dados)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
