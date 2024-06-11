package com.notes.app.backend.services;

import com.notes.app.backend.auth.JwtUtils;
import com.notes.app.backend.entities.Nota;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.notes.app.backend.auth.JwtUtils.*;

@Service
public class NotaServiceImpl implements NotaService{

    @Autowired
    private NotaRepository notesDao;
    @Autowired
    private UsuarioService usersService;

    @Override
    public boolean addNote(Nota note) {
        notesDao.save(note);
        return true;
    }

    @Override
    public List<Nota> getNotesByUserId(long userId) {
        return notesDao.findByUserId(userId);
    }

    @Override
    public List<Nota> getAllNotes() {
        return notesDao.findAll();
    }

    @Override
    public List<Nota> getTop10ByUserId(long userId) {
        List<Nota> list = notesDao.findTop10ByUserIdOrderByCreationTimeDesc(userId);
        return list;
    }

    @Override
    @Transactional
    public void eliminarNotaDeUsuarioPorId(Long id) {
        notesDao.deleteNotaUsuarioPorId(id);
    }


    @Override
    public Optional<Nota> findById(Long id) {
        return notesDao.findById(id);
    }

    @Override
    public Nota guardarNota(Nota nota) {
        return notesDao.save(nota);
    }
}



