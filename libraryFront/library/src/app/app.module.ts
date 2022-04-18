import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LibrarianWindowComponent } from './librarian-window/librarian-window.component';
import { BookComponent } from './book/book.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {LibrarianService} from "./services/librarian.service";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    LibrarianWindowComponent,
    BookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    LibrarianService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
