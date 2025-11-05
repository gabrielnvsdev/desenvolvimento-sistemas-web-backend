package com.example.desenvolvimento_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desenvolvimento_web.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
