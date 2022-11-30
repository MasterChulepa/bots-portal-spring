import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PortalBodyComponent } from './portal-body/portal-body.component';
import { UserInfoComponent } from './portal-body/user-info/user-info.component';
import { FilterInputComponent } from './portal-body/filter-input/filter-input.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    PortalBodyComponent,
    UserInfoComponent,
    FilterInputComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
