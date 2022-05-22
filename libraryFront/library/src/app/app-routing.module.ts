import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LibrarianWindowComponent} from "./librarian-window/librarian-window.component";
import {AuthComponent} from "./auth/auth.component";
import {SubscriberWindowComponent} from "./subscriber-window/subscriber-window.component";
import {CartComponent} from "./subscriber-window/cart/cart.component";

const routes: Routes = [
  {path: '', redirectTo: 'auth', pathMatch: 'full'},
  {path: 'librarian', component: LibrarianWindowComponent},
  {path: 'auth', component: AuthComponent},
  {path: 'subscriber', component: SubscriberWindowComponent},
  {path: 'subscriber/cart', component: CartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
