import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FicheComponent } from './component/fiche/fiche.component';
import { ListUserComponent } from './component/users/list-user/list-user.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuardService } from './service/AuthGuardService.service';
import {SectionComponent} from './component/section/section.component';
import {KanbanbordComponent} from './component/kanbanbord/kanbanbord.component';
import {HomeComponent} from './component/home/home.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'kanban', component: HomeComponent },
  { path: 'kanban/:id', component: KanbanbordComponent },
  { path: 'login', component: LoginComponent},
  { path: 'users', component: ListUserComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
