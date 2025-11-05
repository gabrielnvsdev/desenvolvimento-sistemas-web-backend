package com.example.desenvolvimento_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desenvolvimento_web.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
