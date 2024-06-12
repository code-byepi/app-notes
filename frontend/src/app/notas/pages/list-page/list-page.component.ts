import { Component, OnInit } from '@angular/core';
import { Note } from "../../../interface/note.interface";
import { NoteService } from "../../services/note.service";
import { AuthService } from "../../../auth/services/auth.service";

@Component({
  selector: 'app-list-page',
  templateUrl: './list-page.component.html',
})
export class ListPageComponent implements OnInit {

  notas: Note[] = [];
  notasFiltradas: Note[] = [];
  filtroCategoria: string = '';
  notasArchivadas: Note[] = [];

  constructor(private noteService: NoteService) { }

  ngOnInit(): void {
    this.getNotes();
    this.getArchivedNotes();
  }

  getNotes() {
    this.noteService.getAllNotes().subscribe(
      (data: Note[]) => {
        this.notas = data.filter(nota => !nota.archivado);
        this.filtrarPorCategoria();
      },
      error => {
        console.log('Error al obtener las notas:', error);
      }
    );
  }

  filtrarPorCategoria() {
    if (this.filtroCategoria.trim() === '') {
      this.notasFiltradas = this.notas;
    } else {
      this.notasFiltradas = this.notas.filter(nota => {
        return nota.categorias && nota.categorias.some(categoria =>
          categoria.nombre &&
          categoria.nombre.toLowerCase().includes(this.filtroCategoria.toLowerCase())
        );
      });
    }
  }

  eliminarNota(id: number) {
    if (confirm('¿Estás seguro de eliminar esta nota?')) {
      this.noteService.deleteNoteById(id).subscribe(
        () => {
          // Eliminar la nota del array local después de eliminarla en el servidor
          this.notas = this.notas.filter(nota => nota.noteId !== id);
          this.filtrarPorCategoria();
        },
        error => {
          console.log('Error al eliminar la nota:', error);
        }
      );
    }
  }

  getArchivedNotes() {
    this.noteService.getArchivedNotes().subscribe(
      notas => this.notasArchivadas = notas,
      error => {
        console.log('Error al obtener las notas archivadas:', error);
      }
    );
  }

  archiveNote(event: { id: number; archivar: boolean }) {
    this.noteService.archiveNoteById(event.id, event.archivar).subscribe(
      notaActualizada => {
        if (event.archivar) {
          this.notas = this.notas.filter(n => n.noteId !== event.id);
          this.notasArchivadas.push(notaActualizada);
        } else {
          this.notasArchivadas = this.notasArchivadas.filter(n => n.noteId !== event.id);
          this.notas.push(notaActualizada);
        }
        this.filtrarPorCategoria();
      },
      error => {
        console.error('Error al archivar/desarchivar la nota:', error);
      }
    );
  }

}
