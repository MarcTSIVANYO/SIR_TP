import {UserModel} from './user.model';
import {TagModel} from './tag.model';
import {SectionModel} from './section.model';

export interface FicheModel {
  id: number;
  libelle: string;
  lieu: string;
  url: string;
  dateButoire: number;
  note: string;
  dureeminite: number;
  owner: UserModel;
  section?: SectionModel;
  tags?: TagModel[];
}
