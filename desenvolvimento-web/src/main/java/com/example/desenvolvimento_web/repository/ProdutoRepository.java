package com.example.desenvolvimento_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desenvolvimento_web.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}