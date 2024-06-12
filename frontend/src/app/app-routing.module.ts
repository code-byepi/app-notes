import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Error404PageComponent} from "./shared/pages/error404-page/error404-page.component";
import {PublicGuard} from "./auth/guards/public.guard";
import {AuthGuard} from "./auth/guards/auth.guard";

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
    canActivate: [PublicGuard],
  },
  {
    path: 'notes',
    loadChildren: () => import('./notas/notas.module').then(m => m.NotasModule),
    canActivate: [AuthGuard],
  },
  {
    path: '404',
    component: Error404PageComponent
  },
  {
    path: '',
    redirectTo: 'notes',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: '404',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
