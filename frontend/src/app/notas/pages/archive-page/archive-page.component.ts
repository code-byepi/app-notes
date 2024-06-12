import {Component, OnInit} from '@angular/core';
import {Note} from "../../../interface/note.interface";
import {NoteService} from "../../services/note.service";

@Component({
  selector: 'app-archive-page',
  templateUrl: './archive-page.component.html',
  styles: ``,
})
export class ArchivePageComponent implements OnInit {

  notasArchivadas: Note[] = [];

  constructor(private noteService: NoteService) { }

  ngOnInit(): void {
    this.getArchivedNotes();
  }


  getArchivedNotes() {
    this.noteService.getArchivedNotes().subscribe(
      (data: Note[]) => {
        this.notasArchivadas = data;
      },
      error => {
        console.log('Error al obtener las notas archivadas:', error);
      }
    );
  }


  desarchivarNota(event: { id: number; archivar: boolean }) {
    this.noteService.archiveNoteById(event.id, event.archivar).subscribe(
      notaActualizada => {
        // Filtrar la nota desarchivada de la lista
        this.notasArchivadas = this.notasArchivadas.filter(n => n.noteId !== event.id);
      },
      error => {
        console.error('Error al desarchivar la nota:', error);
      }
    );
  }


}
