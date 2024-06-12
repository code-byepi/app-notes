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

  @Output() archivarNota: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }


  ngOnInit(): void {
    if (!this.nota) throw new Error("No hero");
  }

  onDelete(id: number | undefined) {
    this.eliminarNota.emit(id);
  }

  archivar(id: number | undefined) {
    this.archivarNota.emit(id);
  }


}
