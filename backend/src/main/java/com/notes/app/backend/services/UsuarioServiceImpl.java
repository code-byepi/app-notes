package com.notes.app.backend.services;

import com.notes.app.backend.entities.Nota;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.repositories.NotaRepository;
import com.notes.app.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usersDao;

    @Autowired
    private NotaRepository notaDao;

    @Override
    public String addUser(Usuario user) {
        Usuario u = usersDao.findByEmail(user.getEmail());
        if (u == null) {
            usersDao.save(user);
            return "User Added Successfully";
        } else {
            return "User Already Exists";
        }
    }

    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> list = usersDao.findAll();
        return list;
    }

    @Override
    public Optional<Usuario> getNotesById(Long id) {
        System.out.println(id);
        return usersDao.findById(id);
    }

    @Override
    public Usuario getUserByEmail(String email) {
        return usersDao.findByEmail(email);
    }

    @Override
    public Usuario getUserByEmailAndPassword(String email, String password) {
        Usuario user = usersDao.findByEmailAndPassword(email, password);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nota> listarPorIds(Iterable<Long> ids) {
        return notaDao.findAllById(ids);
    }
}
