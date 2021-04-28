import {Kanban} from './kanban.model';
import {Fiche} from './fiche.model';


export class Section {
  constructor(public id: number, public libelle: string, public position: number, public kanban?: Kanban, public fiches?: Fiche[]) {
  }
}
