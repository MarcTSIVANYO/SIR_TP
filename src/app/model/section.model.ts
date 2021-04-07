import {Kanban} from './kanban.model';
import {Fiche} from './fiche.model';


export interface Section {
  id: number;
  libelle: string;
  position: number;
  kanban: Kanban;
  fiches?: Fiche[];
}
