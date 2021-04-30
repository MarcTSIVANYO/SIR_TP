import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './component/home/home.component';
import {KanbanbordComponent} from './component/kanbanbord/kanbanbord.component';
import {LoginComponent} from './pages/login/login.component';
import {ListUserComponent} from './component/users/list-user/list-user.component';

const routes: Routes = [
  { path: '', redirectTo: 'kanban', pathMatch: 'full'},
  { path: 'kanban', component: HomeComponent },
  { path: 'kanban/:id', component: KanbanbordComponent },
  { path: 'login', component: LoginComponent},
  { path: 'users', component: ListUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
