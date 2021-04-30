import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './component/home/home.component';
import {KanbanbordComponent} from './component/kanbanbord/kanbanbord.component';
import {LoginComponent} from './pages/login/login.component';
import {ListUserComponent} from './component/users/list-user/list-user.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'kanban', pathMatch: 'full'},
  { path: 'kanban', component: HomeComponent },
  { path: 'kanban/:id', component: KanbanbordComponent },
  { path: 'login', component: LoginComponent},
  { path: 'users', component: ListUserComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
