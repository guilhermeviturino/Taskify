package com.Taskifyapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskifyapi.dto.UsuarioDTO;
import com.Taskifyapi.model.Usuario;
import com.Taskifyapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
     @Autowired
        private UsuarioRepository repository;
   
    public UsuarioDTO findById(Long id) {
    
        Usuario usuario = repository.findById(id).get();
        UsuarioDTO dto = new UsuarioDTO(usuario);
        return dto;
    }

}


