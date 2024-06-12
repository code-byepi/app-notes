import { User } from './user.interface';
import { Category } from './category.interface';

export interface Note {
  noteId?: number;
  contenido: string;
  titulo?: string;
  creationTime?: Date;
  user: User;
  archivado: boolean;
  categorias: Category[];
}
