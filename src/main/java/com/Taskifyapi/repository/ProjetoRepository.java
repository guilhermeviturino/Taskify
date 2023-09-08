package com.Taskifyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Taskifyapi.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
    
    @Query("SELECT p FROM tb_projetos p WHERE p.usuario.idUsuario = :idUsuario") //Quando nao se tem uma relaçao direta do atributo de uma outra classe dentro da classe projeto.
    List<Projeto> findByUsuario(@Param("idUsuario") Long idUsuario);
}
