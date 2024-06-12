import { Note } from './note.interface';

export interface Category {
  id?: number;
  nombre?: string;
  notas?: Note[];
}
