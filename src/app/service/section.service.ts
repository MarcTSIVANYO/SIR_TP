import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {Section} from '../model/section.model';
import {HttpClient} from '@angular/common/http';
import {Fiche} from '../model/fiche.model';

const URL = '/api/section/';

@Injectable({
  providedIn: 'root'
})
export class SectionService {

  sections: Section[] = [];
  sectionsSubject = new Subject<Section[]>();

  constructor(private httpClient: HttpClient) { }

  emitSections(): void{
    this.sectionsSubject.next(this.sections.slice());
  }

  getAll(): void {
    this.httpClient
      .get<Section[]>(URL)
      .subscribe(
        (response: Section[]) => {
          this.sections = response;
          this.emitSections();
        },
        (error) => {
          console.error('Erreur lors de la recuperation des Sections ', error);
        }
      );
  }

  createSection(section: Section): void {
    this.httpClient
      .post<Section>(URL, section)
      .subscribe(
        (response: Section) => {
          section = response;
        },
        (error) => {
          console.error(error);
        }
      );
  }

  updateSection(section: Section, id: number): void {
    this.httpClient
      .put<Section>(URL + id, section)
      .subscribe(
        (response: Section) => {
          section = response;
        },
        (error) => {
          console.error('Erreur lors de la mise a jour de la section ', error);
        }
      );
  }

  getSectionByType(sectionType: string): Section {
    let section: any;
    this.httpClient
      .get<Section>(URL + sectionType)
      .subscribe(
        (response: Section) => {
          section = response;
        },
        (error) => {
          console.error(error);
        }
      );
    return section;
  }

  deleteSection(id: number): void {
    this.httpClient
      .delete(URL + id)
      .subscribe();
  }
  getSectionFiches(id: number): Observable<Fiche[]> {
    return this.httpClient.get<Fiche[]>(URL + id + '/fiches');
  }
}
