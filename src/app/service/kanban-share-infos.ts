import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KanbanShareInfos {
  constructor() {}
  public subjectKanbanSelectedId = new Subject<number>();

  // Methode that update le subject
  setKanbanIdValue(id: number): void {
    this.subjectKanbanSelectedId.next(id);
  }

  // Observable to watch the subject
  getObservable(): Subject<any> {
    return this.subjectKanbanSelectedId;
  }
}
