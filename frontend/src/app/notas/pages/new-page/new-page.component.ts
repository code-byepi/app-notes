import { Component } from '@angular/core';
import {Note} from "../../../interface/note.interface";
import {NoteService} from "../../services/note.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-page',
  templateUrl: './new-page.component.html',
})
export class NewPageComponent {

  nota: Note = {
    titulo: '',
    contenido: '',
    archivado: false,
    creationTime: new Date(),
  };

  categorias = [
    { id: 1, nombre: 'Personal' },
    { id: 2, nombre: 'Trabajo' },
    { id: 3, nombre: 'Estudio' },
    { id: 4, nombre: 'Otro' }
  ];

  selectedCategoria: any; // Variable para almacenar la categoría seleccionada


  constructor(private noteService: NoteService, private router: Router) { }


  onSubmit() {
    if (!this.nota.titulo || !this.nota.contenido || !this.selectedCategoria) {
      console.error('Todos los campos son obligatorios');
      return;
    }

    this.nota.categorias = [{ id: this.selectedCategoria }];

    this.noteService.createNote(this.nota).subscribe(
      () => {
        console.log('Nota creada exitosamente');
        this.router.navigate(['/notes/list']); // Redirigir a /notes/list después de crear la nota
      },
      error => {
        console.error('Error al crear la nota', error);
        if (error.error) {
          console.error('Detalles del error:', error.error);
        }
      }
    );
  }

}
