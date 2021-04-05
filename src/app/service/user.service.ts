import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user.model';
import {Observable} from 'rxjs';

const URL = '/api/users/';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient) { }

  signIn(user: User): any {
    //A Coder
  }
}
