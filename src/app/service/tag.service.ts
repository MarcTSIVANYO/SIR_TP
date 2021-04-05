import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class TagService {

  constructor(private httpClient: HttpClient) {}
}
