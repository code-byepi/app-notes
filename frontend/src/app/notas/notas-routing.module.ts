import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LayoutPageComponent} from "./pages/layout-page/layout-page.component";
import {NewPageComponent} from "./pages/new-page/new-page.component";
import {ListPageComponent} from "./pages/list-page/list-page.component";
import {NotePageComponent} from "./pages/note-page/note-page.component";
import {ArchivePageComponent} from "./pages/archive-page/archive-page.component";

const routes: Routes = [
  {
    path: '',
    component: LayoutPageComponent,
    children: [
      { path: 'new-note', component: NewPageComponent },
      { path: 'edit/:id', component: NewPageComponent },
      { path: 'list', component: ListPageComponent },
      { path: 'archived', component: ArchivePageComponent },
      { path: ':id', component: NotePageComponent },
      { path: '**', redirectTo: 'list' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NotasRoutingModule { }
