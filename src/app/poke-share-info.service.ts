import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PokeShareInfoService {

  constructor() { }

  public subjectStringValue = new Subject<string>();

  // Methode that update le subject
  setValue(val: string): void {
    this.subjectStringValue.next(val);
  }

  // Observable to watch the subject
  getObservable(): Subject<any> {
    return this.subjectStringValue;
  }
}
