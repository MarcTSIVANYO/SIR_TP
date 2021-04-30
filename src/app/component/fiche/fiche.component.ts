import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../model/user.model';
import {Fiche} from '../../model/fiche.model';

@Component({
  selector: 'app-fiche',
  templateUrl: './fiche.component.html',
  styleUrls: ['./fiche.component.css']
})
export class FicheComponent implements OnInit {

  @Input() fiche: Fiche;
  constructor() { }

  ngOnInit(): void {
  }

}
