import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {Kanban} from '../model/kanban.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Section} from '../model/section.model';

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
          this.kanbans.push(kanban);
          this.emitKanban();
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

  getKanbanSectionById(kanbanId: number): Observable<Section[]> {
    return this.httpClient.get<Section[]>(URL + kanbanId + '/sections');
  }

  deleteKanban(id: number): void { // A revoir le delete
    this.httpClient
      .delete(URL + id)
      .subscribe();
  }
}
