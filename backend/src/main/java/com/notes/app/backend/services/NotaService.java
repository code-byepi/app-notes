package com.notes.app.backend.services;
import com.notes.app.backend.entities.Nota;
import java.util.List;
import java.util.Optional;


public interface NotaService {

    List<Nota> getAllNotes();

    boolean addNote(Nota note);

    List<Nota> getNotesByUserId(long userId);

    public List<Nota> getTop10ByUserId(long userId, String token);

    void eliminarNotaDeUsuarioPorId(Long id);

    Nota guardarNota(Nota nota);

    Optional<Nota> findById(Long id);

    List<Nota> listarPorIdsYUsuario(List<Long> ids, String emailUsuario);

    List<Nota> obtenerNotasArchivadasPorUsuario(Long userId);
}
