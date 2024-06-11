package com.notes.app.backend.services;
import com.notes.app.backend.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public String addUser(Usuario user);

    public List<Usuario> getAllUsers();

    public Optional<Usuario> getNotesById(Long id);

    public Usuario getUserByEmail(String email);

    public Usuario getUserByEmailAndPassword(String email, String password);


}
