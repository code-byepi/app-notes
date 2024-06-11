package com.notes.app.backend.services;

import com.notes.app.backend.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> obtenerTodasLasCategorias();

    Categoria guardarCategoria(Categoria categoria);

}
