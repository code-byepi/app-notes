import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotasRoutingModule } from './notas-routing.module';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { ListPageComponent } from './pages/list-page/list-page.component';
import { NewPageComponent } from './pages/new-page/new-page.component';
import { NotePageComponent } from './pages/note-page/note-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ToolbarModule } from 'primeng/toolbar';
import {ButtonModule} from "primeng/button";
import { InputSwitchModule } from 'primeng/inputswitch';
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";


@NgModule({
  declarations: [
    LayoutPageComponent,
    ListPageComponent,
    NewPageComponent,
    NotePageComponent
  ],
  imports: [
    CommonModule,
    NotasRoutingModule,
    ReactiveFormsModule,
    ToolbarModule,
    ButtonModule,
    InputSwitchModule,
    FormsModule,
    InputTextModule,
    DialogModule,
  ]
})
export class NotasModule {


}
