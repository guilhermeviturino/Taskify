package com.Taskifyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskifyapi.dto.ProjetoDTO;
import com.Taskifyapi.model.Projeto;
import com.Taskifyapi.repository.ProjetoRepository;

@Service
public class ProjetoService {
    
    @Autowired
    private ProjetoRepository repository;


    public ProjetoDTO findById (Long id) {
        
        Projeto projeto = repository.findById(id).get();
        ProjetoDTO dto = new ProjetoDTO();

        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());

        return dto;
        
    }
}
