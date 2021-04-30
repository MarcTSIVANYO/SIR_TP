import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user.model';
import {Observable} from 'rxjs';


const baseURL = 'api/users';
@Injectable({
  providedIn: 'root'
  })
export class UserService {


  constructor(private httpClient: HttpClient) { }

  signIn(user: User): any {
    //A Coder
  }

  getUsers():Observable<User>{
    return this.httpClient.get<User>(baseURL);
  }

  read(id:number): Observable<User> {
    return this.httpClient.get<User>(`${baseURL}/${id}`);
    }

    create(user:User): Observable<any> {
    return this.httpClient.post(baseURL, user);
    }
    update(id:number, user:User): Observable<any> {
    return this.httpClient.put(`${baseURL}/${id}`, user);
    }
    delete(id:number): Observable<any> {
    return this.httpClient.delete(`${baseURL}/${id}`);
    }
    deleteAll(): Observable<any> {
    return this.httpClient.delete(baseURL);
    }
    searchByName(email : string): Observable<any> {
    return this.httpClient.get(`${baseURL}?email=${email}`);
    }

}
