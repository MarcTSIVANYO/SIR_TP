import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MyComponentComponent } from './my-component/my-component.component';
import {FormsModule} from '@angular/forms';
import { FilterPokemonPipePipe } from './filter-pokemon--pipe.pipe';
import {HttpClientModule} from '@angular/common/http';
import {PokeAPIServiceService} from './poke-apiservice.service';
import { PokedetailComponent } from './pokedetail/pokedetail.component';
import {PokeShareInfoService} from './poke-share-info.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';

@NgModule({
  declarations: [
    AppComponent,
    MyComponentComponent,
    FilterPokemonPipePipe,
    PokedetailComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CalendarModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    PokeAPIServiceService,
    PokeShareInfoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
