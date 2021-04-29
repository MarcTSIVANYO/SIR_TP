import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogService } from 'src/app/service/confirmation-dialog.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  title:String='Liste des utilisateurs'
  headers=['Nom', 'Email', 'Actions']
  users: any;

  currentUser :any= null;
  currentIndex = -1;
  message = '';
  constructor(private userService: UserService, private route: ActivatedRoute,
    private router: Router,private confirmationDialogService: ConfirmationDialogService) { }
  ngOnInit(): void {
    this.message = '';
    this.reloadData();
  }

  reloadData():void {
    this.userService.getUsers()
    .subscribe( data => {
      this.users=data;
    },error => {
      console.log(error);
      });
  }

  refreshList(): void {
    this.reloadData();
    this.currentUser = null;
    this.currentIndex = -1;
  }

  setActiveUser(user:any, index:number): void {
    this.currentUser = user;
    this.currentIndex = index;
  }

  updateUser(): void {
    this.userService.update(this.currentUser.id, this.currentUser)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'Utilisateur modifé avec succès!';
        },
        error => {
          console.log(error);
        });
  }

  deleteUser(): void {
    this.confirmationDialogService.confirm('Confirmation', 'Voulez-vous vraiment supprimer cet utilisateur?')
    .then((confirmed) =>  {
      if(confirmed){
      this.userService.delete(this.currentUser.email)
      .subscribe(
        response => {
          console.log(response);
          this.reloadData();
          this.message = 'Utilisateur supprimé avec succès!';
          this.currentUser = null;
         // this.router.navigate(['/users']);
        },
        error => {
          console.log(error);
        });
    }
  }).catch(() => console.log("L'utilisateur a fermé la boîte de dialogue (par exemple, en utilisant ESC, en cliquant sur l'icône en forme de croix ou en cliquant en dehors de la boîte de dialogue)"));
  }

}
