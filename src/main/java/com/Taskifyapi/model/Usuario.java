package com.Taskifyapi.model;

import com.Taskifyapi.constants.Genero;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // Gera os getters e setters automaticamente
@NoArgsConstructor  // Contrutor vazio
@AllArgsConstructor     // Contrutor declarado
@Entity(name = "tb_usuarios")   // Definir um nome para a tabela no banco de dados
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)    //Id serve para chave primaria, com valor alto gerado.
    private Long idUsuario;

    @Column(nullable = false, length = 50)   //Colum serve para dizer se vai ser obrigatorio ser preenchido no banco de dados (false = not null)
    private String nome;

    @Column(nullable = false, length = 150)
    private String sobrenome;

    @Column(nullable = false, unique = true)    //unique serve para dizer que nao pode email iguais (true para confirmar, false ignora)
    private String email;

    @Column(nullable = false, length = 10)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Embedded                                     // serve para imbutir na tabela usuario no banco de dados.
    private Endereco endereco;
}
