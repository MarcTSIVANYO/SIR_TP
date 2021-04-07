import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';
import {Kanban} from '../model/kanban.model';
import {HttpClient} from '@angular/common/http';

const URL = '/api/kanban/';
@Injectable()
export class KanbanService {

  kanbans: Kanban[] = [];
  kanbansSubject = new Subject<Kanban[]>();

  constructor(private httpClient: HttpClient) { }

  emitKanban(): void{
    this.kanbansSubject.next(this.kanbans.slice());
  }

  getAll(): void {
    this.httpClient
      .get<Kanban[]>(URL)
      .subscribe(
        (response: Kanban[]) => {
          this.kanbans = response;
          this.emitKanban();
        },
        (error) => {
          console.error('Erreur lors de la recuperation des Kanbans ', error);
        }
      );
  }

  createKanban(kanban: Kanban): void {
    this.httpClient
      .post<Kanban>(URL, kanban)
      .subscribe(
        (response: Kanban) => {
          kanban = response;
        },
        (error) => {
          console.error(error);
        }
      );
  }

  updateKanban(kanban: Kanban, id: number): void {
    this.httpClient
      .put<Kanban>(URL + id, kanban)
      .subscribe(
        (response: Kanban) => {
          kanban = response;
        },
        (error) => {
          console.error('Erreur lors de la mise a jour du kanban ', error);
        }
      );
  }

  getKanbanById(id: number): Kanban {
    let kanban: any;
    this.httpClient
      .get<Kanban>(URL + id)
      .subscribe(
        (response: Kanban) => {
          kanban = response;
        },
        (error) => {
          console.error(error);
        }
      );
    return kanban;
  }

  deleteKanban(id: number): void { // A revoir le delete
    this.httpClient
      .delete(URL + id)
      .subscribe();
  }
}
