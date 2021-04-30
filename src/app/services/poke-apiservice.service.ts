import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PokeDetail, PokemonServiceRes} from './pokemon';

const URL = 'https://pokeapi.co/api/v2/pokemon/';

@Injectable({
  providedIn: 'root'
})
export class PokeAPIServiceService {

  constructor(private httpClient: HttpClient) { }

  getPokemons(): Observable<PokemonServiceRes> {
    return this.httpClient.get<PokemonServiceRes>(URL);
  }
  getPokemonInfo(id: string): Observable<PokeDetail> {
    return this.httpClient.get<PokeDetail>(URL + id + '/');
  }

}
