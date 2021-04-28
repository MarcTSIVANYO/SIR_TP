import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../model/user.model';

@Component({
  selector: 'app-fiche',
  templateUrl: './fiche.component.html',
  styleUrls: ['./fiche.component.css']
})
export class FicheComponent implements OnInit {

  @Input() ficheId = '#26';
  @Input() ficheLibelle = 'JaxRS et OpenAPI';
  description = 'API Java permettant de créer des services Web avec une architecture REST';
  @Input() ficheDate = new Date();
  @Input() ficheDuree = '3h';
  @Input() ficheOwner = new User(1, 'alpha@gmail.com', 'Alpha Beta', '');
  @Input() ficheUrl = 'https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel';
  constructor() { }

  ngOnInit(): void {
  }

}
