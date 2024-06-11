import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {catchError, map, tap} from "rxjs/operators";
import { Observable, of } from "rxjs";
import { User } from "../interface/user.interface";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private user?: User;
  private baseUrl: string = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/usuarios`, { email, password }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token); // Almacena el token JWT en el localStorage
      })
    );
  }

  checkAuthentication(): Observable<boolean> {
    const token = localStorage.getItem('token');
    if (!token) return of(false);

    // Verificar la autenticación usando el token JWT
    return this.http.get<User>(`${this.baseUrl}/usuario`).pipe(
      tap(user => this.user = user),
      map(user => !!user),
      catchError(() => {
        this.logout(); // Limpiar el almacenamiento local en caso de error de autenticación
        return of(false);
      })
    );
  }

  logout() {
    this.user = undefined;
    localStorage.removeItem('token'); // Elimina el token del localStorage
  }
}
