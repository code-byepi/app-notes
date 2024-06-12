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
  createNote(note: Note): Observable<Note> {
    const token = localStorage.getItem('token');
    return this.http.post<Note>(`${this.baseUrl}/notes`, note, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
  }

  deleteNoteById(id: number): Observable<void> {
    const token = localStorage.getItem('token');
    return this.http.delete<void>(`${this.baseUrl}/delete-notes/${id}`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
  }

  archiveNoteById(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/notes/${id}/archivar`, {});
  }

  getArchivedNotes(): Observable<Note[]> {
    const token = localStorage.getItem('token');
    return this.http.get<Note[]>(`${this.baseUrl}/notas-archivadas`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
  }


}
