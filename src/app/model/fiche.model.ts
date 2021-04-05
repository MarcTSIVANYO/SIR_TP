import {Tag} from 'primeng/tag';
import {User} from './user.model';
import {Section} from './section.model';

export interface Fiche {
  id: number;
  libelle: string;
  lieu: string;
  url: string;
  dateButoire: number;
  note: string;
  dureeminite: number;
  owner: User;
  section?: Section;
  tags?: Tag[];
}
