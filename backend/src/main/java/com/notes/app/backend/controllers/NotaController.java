package com.notes.app.backend.controllers;

import com.notes.app.backend.entities.Categoria;
import com.notes.app.backend.entities.Nota;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.repositories.CategReposity;
import com.notes.app.backend.services.CategoriaService;
import com.notes.app.backend.services.NotaService;
import com.notes.app.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private CategoriaService categoriaService;
    @Autowired
    private CategReposity categReposity;

    // localhost:8080/notes
    @PostMapping(path = "/notes", consumes = { "application/json" })
    public ResponseEntity<String> addNote(@RequestBody Nota note, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.getUserByEmail(email);

        if (note.getCategorias() == null || note.getCategorias().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Categoria categoria = note.getCategorias().iterator().next();

        if (!categReposity.existsById(categoria.getId())) {
            return ResponseEntity.badRequest().body(null); // Devolver un error si la categoría no existe
        }

        note.setUser(usuario); // Asocia el usuario a la nota
        notaService.addNote(note);
        return ResponseEntity.ok("Se añadió la nota de forma exitosa!");
    }

    // localhost:8080/notes/id/{id}
    @GetMapping("notes/id/{id}")
    public List<Nota> getNotesByUserId(@PathVariable("id") Long userId) {
        return notaService.getTop10ByUserId(userId);
    }

    // localhost:8080/notes
    @GetMapping(path = "/notes")
    public List<Nota> allNotes() {
        return notaService.getAllNotes();
    }

    /*@DeleteMapping("/notes/{id}")
    public void deleteNoteByNoteId(@PathVariable("id") Long noteId) {
        notaService.deletebyNoteId(noteId);
    }*/
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
                nota.setContenido("No tienes permiso para modificar esta nota"); // o agregar un nuevo campo para el mensaje de error
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(nota);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
