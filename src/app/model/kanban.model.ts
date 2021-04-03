import {UserModel} from './user.model';
import {SectionModel} from './section.model';

export interface KanbanModel {
  id: number;
  nom: string;
  admin: UserModel;
  sections?: SectionModel[];
}
