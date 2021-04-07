import {User} from './user.model';
import {Section} from './section.model';


export interface Kanban {
  id: number;
  nom: string;
  admin: User;
  sections?: Section[];
}
