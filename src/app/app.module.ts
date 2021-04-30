import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FooterComponent} from './layout/footer/footer.component';
import {FicheComponent} from './component/fiche/fiche.component';
import {LoginComponent} from './pages/login/login.component';
import {HeaderComponent} from './layout/header/header.component';
import {ListUserComponent} from './component/users/list-user/list-user.component';
import {SectionComponent} from './component/section/section.component';
import {KanbanbordComponent} from './component/kanbanbord/kanbanbord.component';
import {HomeComponent} from './component/home/home.component';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {KanbanService} from './service/kanban.service';
import {TagService} from './service/tag.service';
import {SectionService} from './service/section.service';
import {FicheService} from './service/fiche.service';
import {UserService} from './service/user.service';
import {KanbanShareInfos} from './service/kanban-share-infos';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ConfirmationDialogComponent} from './layout/layout/confirmation-dialog/confirmation-dialog.component';
import {AddUserComponent} from './component/users/add-user/add-user.component';
import {ConfirmationDialogService} from './service/confirmation-dialog.service';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import { TagComponent } from './component/tags/tag/tag.component';

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
    HomeComponent,
    ConfirmationDialogComponent,
    AddUserComponent,
    NotFoundComponent,
    TagComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CommonModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    KanbanService,
    FicheService,
    SectionService,
    TagService,
    UserService,
    KanbanShareInfos,
    ConfirmationDialogService
  ],
  entryComponents: [ ConfirmationDialogComponent ],
  bootstrap: [AppComponent]
})
export class AppModule { }
