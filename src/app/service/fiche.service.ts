import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Fiche} from '../model/fiche.model';
import {Subject} from 'rxjs';

const URL = '/api/fiches/';

@Injectable()
export class FicheService {

  fiches: Fiche[] = [];
  fichesSubject = new Subject<Fiche[]>();

  constructor(private httpClient: HttpClient) {}

  emitFiches(): void{
    this.fichesSubject.next(this.fiches.slice());
  }

  getAll(): void {
    this.httpClient
      .get<Fiche[]>(URL)
      .subscribe(
        (response: Fiche[]) => {
          this.fiches = response;
          this.emitFiches();
        },
        (error) => {
          console.error('Erreur lors de la recuperation des Fiches ', error);
        }
      );
  }

  createFiche(fiche: Fiche): void {
    this.httpClient
      .post<Fiche>(URL, fiche)
      .subscribe(
        (response: Fiche) => {
          fiche = response;
        },
        (error) => {
          console.error(error);
        }
      );
  }

  updateFiche(fiche: Fiche, id: number): void {
    this.httpClient
      .put<Fiche>(URL + id, fiche)
      .subscribe(
        (response: Fiche) => {
          fiche = response;
        },
        (error) => {
          console.error('Erreur lors de la mise a jour de la fiche ', error);
        }
      );
  }

  getFicheById(id: number): Fiche {
    let fiche: any;
    this.httpClient
      .get<Fiche>(URL + id)
      .subscribe(
        (response: Fiche) => {
          fiche = response;
        },
        (error) => {
          console.error(error);
        }
      );
    return fiche;
  }

  deleteFiche(id: number): void { // A revoir
    this.httpClient
      .delete(URL + id)
      .subscribe();
  }
}
