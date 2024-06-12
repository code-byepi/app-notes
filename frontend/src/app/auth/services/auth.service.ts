import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { catchError, map, tap } from "rxjs/operators";
import { Observable, of } from "rxjs";
import { User } from "../../interface/user.interface";
import { JwtResponse } from '../../interface/jwt-response.interface';
import {jwtDecode} from "jwt-decode";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user?: User;

  private baseUrl: string = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  register(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/register/user`, user).pipe(
      catchError(this.handleError('register', []))
    );
  }

  login(email: string, password: string): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.baseUrl}/login/user`, { email, password }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);

        const decodedToken = JSON.parse(atob(response.token.split('.')[1]));
        const userId = decodedToken.id;
        localStorage.setItem('userId', userId.toString());
      })
    );
  }

  logout() {
    this.user = undefined;
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
