import {Component, Input, OnInit} from '@angular/core';
import {Note} from "../../interface/note.interface";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
})
export class CardComponent implements OnInit{

  @Input()
  public nota!: Note;

  ngOnInit(): void {
    if (!this.nota) throw new Error("No hero");
  }


}
