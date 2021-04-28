import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { LoginComponent } from './pages/login/login.component';
import {KanbanService} from './service/kanban.service';
import {FicheService} from './service/fiche.service';
import {SectionService} from './service/section.service';
import {TagService} from './service/tag.service';
import {UserService} from './service/user.service';
import { FicheComponent } from './component/fiche/fiche.component';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ListUserComponent } from './component/users/list-user/list-user.component';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import {SectionComponent} from './component/section/section.component';
import { KanbanbordComponent } from './component/kanbanbord/kanbanbord.component';
import { HomeComponent } from './component/home/home.component';
import {KanbanShareInfos} from './service/kanban-share-infos';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    FicheComponent,
    ListUserComponent,
    SectionComponent,
    KanbanbordComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CommonModule,
    HttpClientModule
  ],
  providers: [
    KanbanService,
    FicheService,
    SectionService,
    TagService,
    UserService,
    KanbanShareInfos
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
