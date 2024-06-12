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


}
