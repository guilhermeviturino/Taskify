package com.Taskifyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Taskifyapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email); // Criado para filtrar um usuário por email,
                                                 // pois o findByEmail nao existe.
    
}
