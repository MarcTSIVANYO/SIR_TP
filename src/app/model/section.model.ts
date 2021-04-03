import {KanbanModel} from './kanban.model';
import {FicheModel} from './fiche.model';

export interface SectionModel {
  id: number;
  libelle: string;
  position: number;
  kanban: KanbanModel;
  fiches?: FicheModel[];
}
