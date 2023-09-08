package com.Taskifyapi.dto;


import com.Taskifyapi.constants.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    
    private String nome;
    private String descricao;
    Status status;
}
