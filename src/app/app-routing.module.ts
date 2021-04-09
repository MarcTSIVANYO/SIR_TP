import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FicheComponent } from './component/fiche/fiche.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuardService } from './service/AuthGuardService.service';

const routes: Routes = [
  { path: 'home', component: FicheComponent },
  { path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
