package com.notes.app.backend.services;

import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// clase para para cargar detalles de usuarios - Autorización y Autenticación
@Service
public class CustomUserDetailService  implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // método que se invoca cuando un usuario intenta autenticarse
    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(username); // busco usuario en la base de datos por email
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado!");
        }
        return user; // retorno el usuario para spring security
    }


}
