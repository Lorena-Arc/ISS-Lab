import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LibrarianWindowComponent } from './librarian-window/librarian-window.component';
import { BookComponent } from './book/book.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {LibrarianService} from "./services/librarian.service";
import {ReactiveFormsModule} from "@angular/forms";
import { AuthComponent } from './auth/auth.component';
import { SubscriberWindowComponent } from './subscriber-window/subscriber-window.component';
import { BookSubscriberComponent } from './subscriber-window/book-subscriber/book-subscriber.component';
import { CartComponent } from './subscriber-window/cart/cart.component';
import {SubscriberService} from "./services/subscriber.service";
import {TokenInterceptor} from "./utils/token.interceptor";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";

@NgModule({
  declarations: [
    AppComponent,
    LibrarianWindowComponent,
    BookComponent,
    AuthComponent,
    SubscriberWindowComponent,
    BookSubscriberComponent,
    CartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 1000,
      positionClass: 'toast-bottom-center',
      preventDuplicates: true,
    })
  ],
  providers: [
    LibrarianService,
    SubscriberService,
    {
      provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
