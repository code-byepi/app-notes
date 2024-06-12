import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable } from "rxjs";
import { Note } from "../../interface/note.interface";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private baseUrl: string = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getAllNotes(): Observable<Note[]> {
    const token = localStorage.getItem('token');
    return this.http.get<Note[]>(`${this.baseUrl}/mis-notas`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
  }

}
