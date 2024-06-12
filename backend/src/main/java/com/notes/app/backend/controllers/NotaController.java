package com.notes.app.backend.controllers;

import com.notes.app.backend.entities.Categoria;
import com.notes.app.backend.entities.Nota;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.repositories.CategReposity;
import com.notes.app.backend.services.CategoriaService;
import com.notes.app.backend.services.NotaService;
import com.notes.app.backend.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@CrossOrigin()
@RestController
public class NotaController {

    @Autowired
    private NotaService notaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CategReposity categReposity;

    // localhost:8080/notes
    @PostMapping(path = "/notes", consumes = { "application/json" })
    public ResponseEntity<String> addNote(@RequestBody Nota note, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.getUserByEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        if (note.getCategorias() == null || note.getCategorias().isEmpty()) {
            return ResponseEntity.badRequest().body("La nota debe tener al menos una categoría");
        }

        Categoria categoria = note.getCategorias().iterator().next();

        if (!categReposity.existsById(categoria.getId())) {
            return ResponseEntity.badRequest().body("La categoría no existe");
        }

        note.setUser(usuario); // Asocia el usuario a la nota
        notaService.addNote(note);
        return ResponseEntity.ok("Se añadió la nota de forma exitosa!");
    }


    // localhost:8080/notes
    @GetMapping(path = "/notes")
    public List<Nota> allNotes() {
        return notaService.getAllNotes();
    }

    // notas por usuario
    @GetMapping("/mis-notas")
    public ResponseEntity<?> obtenerMisNotas(Principal principal) {
        String emailUsuario = principal.getName();
        Usuario usuario = usuarioService.getUserByEmail(emailUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<Nota> notas = notaService.getNotesByUserId(usuario.getUserId());

        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @DeleteMapping("delete-notes/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long id){
        notaService.eliminarNotaDeUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/notes/{id}/archivar")
    public ResponseEntity<Nota> archivarNota(@PathVariable Long id, Principal principal) {
        String emailUsuario = principal.getName();
        Optional<Nota> notaOptional = notaService.findById(id);

        if (notaOptional.isPresent()) {
            Nota nota = notaOptional.get();
            if (nota.getUser().getEmail().equals(emailUsuario)) {
                nota.setArchivado(!nota.isArchivado());
                return ResponseEntity.ok(notaService.guardarNota(nota));
            } else {
                nota.setContenido("No tienes permiso para modificar esta nota");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(nota);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // no la uso en el frontend
    @PutMapping("/notes/{id}/desarchivar")
    public ResponseEntity<Nota> desarchivarNota(@PathVariable Long id, Principal principal) {
        String emailUsuario = principal.getName();
        Optional<Nota> notaOptional = notaService.findById(id);

        if (notaOptional.isPresent()) {
            Nota nota = notaOptional.get();
            if (nota.getUser().getEmail().equals(emailUsuario)) {
                nota.setArchivado(!nota.isArchivado());
                return ResponseEntity.ok(notaService.guardarNota(nota));
            } else {
                nota.setContenido("No tienes permiso para modificar esta nota");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(nota);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Notas archivadas del usuario
    @GetMapping("/notas-archivadas")
    public ResponseEntity<List<Nota>> obtenerNotasArchivadas(Principal principal) {
        String emailUsuario = principal.getName();
        Usuario usuario = usuarioService.getUserByEmail(emailUsuario);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Nota> notasArchivadas = notaService.obtenerNotasArchivadasPorUsuario(usuario.getUserId());
        return ResponseEntity.ok(notasArchivadas);
    }



    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
