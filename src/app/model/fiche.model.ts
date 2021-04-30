import {User} from './user.model';
import {Section} from './section.model';
import { Tag } from './tag';

export interface Fiche {
  id: number;
  libelle: string;
  lieu: string;
  url: string;
  dateButoire: Date;
  note: string;
  dureeminite: number;
  owner?: User;
  sectionId?: number;
  tags?: Tag[];
}
