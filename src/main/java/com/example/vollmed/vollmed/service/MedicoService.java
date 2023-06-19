package com.example.vollmed.vollmed.service;

import com.example.vollmed.vollmed.entities.medico.DTOs.DTOAtualizacaoMedico;
import com.example.vollmed.vollmed.entities.medico.DTOs.DTOListagemMedico;
import com.example.vollmed.vollmed.entities.medico.Medico;
import com.example.vollmed.vollmed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Medico cadastrarMedico(Medico medico) {
        medico.setTelefone(medico.getTelefone().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
        return repository.save(medico);
    }

    public void deletarMedico(Long id) {
        repository.getReferenceById(id).excluir();
    }

    public Medico buscarMedicoPorId(Long id) {
        return repository.getReferenceById(id);
    }

    public Medico atualizarMedicoPorId(DTOAtualizacaoMedico dados) {
        return repository.getReferenceById(dados.id()).atualizarInformacoes(dados);
    }

    public Page<DTOListagemMedico> listarMedicos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DTOListagemMedico::new);
    }
}
