import { Component, OnInit } from '@angular/core';
import {PokeDetail, Pokemon} from '../models/pokemon';
import { PokeAPIServiceService } from '../services/poke-apiservice.service';
import { PokeShareInfoService } from '../services/poke-share-info.service';

@Component({
  selector: 'app-my-component',
  templateUrl: './my-component.component.html',
  styleUrls: ['./my-component.component.css']
})
export class MyComponentComponent implements OnInit {

  id: string;
  selectedPokId: string;
  searchPokeName = '';
  pokes: Pokemon[] = [];
  pokeDetail: PokeDetail;
  myDate: Date;
  constructor(private pokemonAPI: PokeAPIServiceService,
              private pokeShareInfoService: PokeShareInfoService) {}

  ngOnInit(): void {
    this.pokemonAPI.getPokemons().subscribe((data) => {
      data.results.forEach((e, index) => {
        index += 1;
        this.pokes.push(new Pokemon('' + index, e.name, e.url));
      });
    });
  }

  onGo() {

    if (this.selectedPokId !== '') {
      this.pokemonAPI.getPokemonInfo(this.selectedPokId).subscribe((data) => {
        this.pokeDetail = data;
        this.pokeShareInfoService.setValue(this.selectedPokId);
      });
    }
  }

}
