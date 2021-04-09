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

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    FicheComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CommonModule
  ],
  providers: [
    KanbanService,
    FicheService,
    SectionService,
    TagService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
