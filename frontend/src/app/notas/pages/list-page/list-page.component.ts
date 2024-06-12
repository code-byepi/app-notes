import { Component, OnInit } from '@angular/core';
import { Note } from "../../../interface/note.interface";
import { NoteService } from "../../services/note.service";
import {AuthService} from "../../../auth/services/auth.service";

@Component({
  selector: 'app-list-page',
  templateUrl: './list-page.component.html',
})
export class ListPageComponent implements OnInit {

  notas: Note[] = [];

  constructor(private noteService: NoteService) { }

  ngOnInit(): void {
    this.getNotes();
  }

  getNotes() {
    this.noteService.getAllNotes().subscribe(
      (data: Note[]) => {
        this.notas = data;
      },
      error => {
        console.log('Error al obtener las notas:', error);
      }
    );
  }
}
