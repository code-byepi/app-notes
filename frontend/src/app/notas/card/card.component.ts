import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Note} from "../../interface/note.interface";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
})
export class CardComponent implements OnInit{

  @Input()
  public nota!: Note;

  @Output()
  eliminarNota = new EventEmitter<number>();

  @Output() archivarNota = new EventEmitter<{ id: number; archivar: boolean }>();


  constructor() { }


  ngOnInit(): void {
    if (!this.nota) throw new Error("No hero");
  }

  onDelete(id: number | undefined) {
    this.eliminarNota.emit(id);
  }

  archivar(id: number | undefined, archivar: boolean) {
    this.archivarNota.emit({ id: id!, archivar }); // Emitir un objeto con id y archivar
  }



}
