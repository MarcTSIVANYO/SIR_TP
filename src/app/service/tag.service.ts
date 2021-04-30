import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Tag } from '../model/tag';
const baseURL = 'api/tag';

@Injectable()
export class TagService {
  submitted = false;
  tags: Tag[] = [];
  tagsSubject = new Subject<Tag[]>();
  constructor(private httpClient: HttpClient) {}

  emitTags(): void {
    this.tagsSubject.next(this.tags.slice());
  }

  getTags(): void {
    this.httpClient.get<Tag[]>(baseURL).subscribe((reponse: Tag[]) => {
      this.tags = reponse;
      this.emitTags();
    });
  }

  read(id: number): Observable<Tag> {
    return this.httpClient.get<Tag>(`${baseURL}/${id}`);
  }

  create(tag: Tag): void {
    this.httpClient.post(baseURL, tag).subscribe((reponse: Tag) => {
      this.tags.push(reponse);
      this.emitTags();
    });
  }
  update(id: number, tag: Tag): Observable<any> {
    return this.httpClient.put(`${baseURL}/${id}`, tag);
  }
  delete(id: number): Observable<any> {
    return this.httpClient.delete(`${baseURL}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.httpClient.delete(baseURL);
  }
}
