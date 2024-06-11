package com.notes.app.backend.services;
import com.notes.app.backend.entities.Categoria;
import com.notes.app.backend.repositories.CategReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategReposity categoriaRepository;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {

        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }
        try {
            return categoriaRepository.save(categoria);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al guardar la categoría: " + e.getMessage(), e);
        } catch (Exception e) {

            throw new RuntimeException("Error inesperado al guardar la categoría", e);
        }
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

}
