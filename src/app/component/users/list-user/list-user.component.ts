import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  title:String='Liste des utilisateurs'
  headers=['Nom', 'Email', 'Email', 'Actions']
  //users:Observable<User>[]=[]
  users:User[]=[]
  constructor(private userService: UserService) { }

  ngOnInit(): void {
//https://www.devglan.com/angular/angular-8-crud-example
    //this.users.push(this.userService.getUsers());
    this.userService.getUsers()
    .subscribe( data => {
      this.users.push(data);
    },error => {
      console.log(error);
      });
  }

  deleteUser(user:User){
    this.userService.delete(user.id);
  }
}
