package com.notes.app.backend.repositories;

import com.notes.app.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Para registrar
    Usuario findByEmail(String email);

    // Para el login
    Usuario findByEmailAndPassword(String email, String password);

}