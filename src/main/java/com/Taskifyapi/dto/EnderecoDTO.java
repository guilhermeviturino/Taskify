package com.Taskifyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    
    private Long idUsuario;
    private String cep;
    private String complemento;
    private String numero;
}
