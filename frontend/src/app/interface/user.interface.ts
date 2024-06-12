import {Note} from "./note.interface";

export interface User {
  userId?: number;
  email: string;
  password: string;
  notes?: Note[];
}
