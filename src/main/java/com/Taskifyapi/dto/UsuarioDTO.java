package com.Taskifyapi.dto;

import com.Taskifyapi.constants.Genero;
import com.Taskifyapi.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    private String nome;
    private String email;
    private Genero genero;

    public UsuarioDTO(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        genero = usuario.getGenero();
    }
}
