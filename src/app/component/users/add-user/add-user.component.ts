import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css'],
})
export class AddUserComponent implements OnInit {
  title = 'Ajouter un nouveau utilisateur';
  fieldTextType: boolean = false;
  users: any;
  user = {
    email: '',
    name: '',
    password: '',
  };
  submitted = false;
  requiered = false;

  constructor(private router: Router, private userService: UserService) {}

  ngOnInit(): void {}

  saveuser(): void {
    if (
      this.user.email != '' &&
      this.user.name != '' &&
      this.user.password != ''
    ) {
      const data: User = {
        email: this.user.email,
        name: this.user.name,
        password: this.user.password,
      };

      this.userService.create(data).subscribe(
        (response) => {
          console.log(response);
          this.userService.reloadSubmitData(true);
          this.router.navigate(['../users']);
          this.emptyFilds();
          this.submitted = true;
          this.requiered = false;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      this.requiered = true;
    }
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

  emptyFilds(): void {
    this.user = {
      email: '',
      name: '',
      password: '',
    };
  }
}
