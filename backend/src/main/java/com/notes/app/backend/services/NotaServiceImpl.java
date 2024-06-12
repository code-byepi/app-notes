package com.notes.app.backend.services;

import com.notes.app.backend.auth.JwtUtils;
import com.notes.app.backend.entities.Nota;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaServiceImpl implements NotaService{

    @Autowired
    private NotaRepository notesDao;
    @Autowired
    private UsuarioService usersService;

    @Autowired
    private JwtUtils jwtUtils;

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
    public List<Nota> getTop10ByUserId(long userId, String token) {

        Long userIdFromToken = jwtUtils.extractId(token);

        // Verificar si el ID del usuario en el token coincide con el ID proporcionado
        if (!userIdFromToken.equals(userId)) {
            throw new AccessDeniedException("No tienes permiso para acceder a estas notas");
        }

        return notesDao.findTop10ByUserIdOrderByCreationTimeDesc(userId);
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

    @Override
    public List<Nota> listarPorIdsYUsuario(List<Long> ids, String emailUsuario) {
        Usuario usuario = usersService.getUserByEmail(emailUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<Nota> notas = notesDao.findAllById(ids);
        // Filtrar las notas que pertenecen al usuario
        return notas.stream()
                .filter(nota -> nota.getUser().equals(usuario))
                .collect(Collectors.toList());
    }

    @Override
    public List<Nota> obtenerNotasArchivadasPorUsuario(Long userId) {
        return notesDao.findByUserUserIdAndArchivado(userId, true);
    }
}



