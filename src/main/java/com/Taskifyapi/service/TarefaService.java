package com.Taskifyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskifyapi.dto.TarefaDTO;
import com.Taskifyapi.model.Tarefa;
import com.Taskifyapi.repository.TarefaRepository;


@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository repository;

    public TarefaDTO findById(Long id) {
        Tarefa tarefa = repository.findById(id).get();
        TarefaDTO dto = new TarefaDTO();

        dto.setNome(tarefa.getNome());
        dto.setDescricao(tarefa.getDescricao());
        dto.setStatus(tarefa.getStatus());

        return dto;
        
    }
}
