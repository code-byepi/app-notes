package com.notes.app.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental por la base de datos
    private Long noteId;

    @Column(nullable = false)
    private String contenido;

    @Column
    private String titulo;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false)
    private LocalDateTime creationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @Column(nullable = false)
    private boolean archivado;

    @ManyToMany
    @JoinTable(
            name = "nota_categoria",
            joinColumns = @JoinColumn(name = "nota_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias;

    // Constructor vacío (requerido por JPA)
    public Nota() {}

    // Constructor con parámetros (sin userId)
    public Nota(String contenido, String titulo, Usuario user, Set<Categoria> categorias, boolean archivado) {
        this.contenido = contenido;
        this.titulo = titulo;
        this.user = user;
        this.categorias = categorias;
        this.archivado = archivado;
    }

    // Getters y setters para todos los campos
    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public boolean isArchivado() {
        return archivado;
    }

    public void setArchivado(boolean archivado) {
        this.archivado = archivado;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    // toString(), hashCode() y equals() (sin userId)
    @Override
    public String toString() {
        return "Nota [noteId=" + noteId + ", contenido=" + contenido + ", titulo=" + titulo +
                ", creationTime=" + creationTime + ", user=" + user + ", archivado=" + archivado + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(contenido, creationTime, noteId, titulo, user, archivado);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nota other = (Nota) obj;
        return Objects.equals(contenido, other.contenido) && Objects.equals(creationTime, other.creationTime)
                && Objects.equals(noteId, other.noteId) && Objects.equals(titulo, other.titulo)
                && Objects.equals(user, other.user) && archivado == other.archivado;
    }
}
