import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogService } from 'src/app/service/confirmation-dialog.service';
import { UserService } from 'src/app/service/user.service';
import {User} from '../../../model/user';
import {Kanban} from '../../../model/kanban.model';
import {Subscription} from 'rxjs';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  title:String='Liste des utilisateurs'
  headers=['Nom', 'Email', 'Actions']
  users: User[] = [];
  userSubscription: Subscription;
  addUserForm: FormGroup;

  currentUser :any= null;
  currentIndex = -1;
  message = '';
  fieldTextType = false;
  constructor(private userService: UserService, private route: ActivatedRoute, private confirmationDialogService: ConfirmationDialogService,
              private formBuilder: FormBuilder, private modalService: NgbModal) {
    this.userSubscription = new Subscription();
  }
  ngOnInit(): void {
    this.message = '';
    this.initFomulaire();

    this.userSubscription = this.userService.usersSubject.subscribe(
      (response: User[]) => {
        this.users = response;
      }
    );
    this.reloadData();

  }

  reloadData(): void {
    this.userService.getUsers();
    this.users = this.userService.users;
    console.log(this.userService.users);
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

  toggleFieldTextType(): void {
    this.fieldTextType = !this.fieldTextType;
  }

  onSubmit(): void {
    this.modalService.dismissAll();
    this.saveuser();
  }

  initFomulaire(): void {
    this.addUserForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  openModal(addUserModal): void {
    this.modalService.open(addUserModal, {
      centered: true,
      backdrop: 'static'
    });
  }

  saveuser(): void {
      const data: User = {
        email: this.addUserForm.value.email,
        name: this.addUserForm.value.name,
        password: this.addUserForm.value.password,
      };

      this.userService.create(data);
     /* this.userService.reloadSubmitData(true);
      this.router.navigate(['../users']);
      this.emptyFilds();
      this.submitted = true;
      this.requiered = false;
    } else {
      this.requiered = true;
    }
    this.userService.getUsers();*/
  }

}
