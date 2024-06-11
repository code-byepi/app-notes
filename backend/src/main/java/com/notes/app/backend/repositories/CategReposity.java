package com.notes.app.backend.repositories;

import com.notes.app.backend.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategReposity extends JpaRepository<Categoria, Long> {


}