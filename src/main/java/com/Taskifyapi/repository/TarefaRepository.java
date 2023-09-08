package com.Taskifyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Taskifyapi.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :idProjeto")
    List<Tarefa> findByProjeto(@Param("idProjeto")Long idProjeto);

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = :idUsuario") //Query pra buscar tarefas que um usuário especifico esteja participando.
    List<Tarefa> findByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :idProjeto AND t.usuario.idUsuario = :idUsuario")
    List<Tarefa> findByProjetoAndUsuario(@Param("idProjeto")Long idProjeto, @Param("idUsuario") Long idUsuario);
}