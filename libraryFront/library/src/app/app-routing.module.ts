import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LibrarianWindowComponent} from "./librarian-window/librarian-window.component";

const routes: Routes = [
  {path: '', redirectTo: 'librarian', pathMatch: 'full'},
  {path: 'librarian', component: LibrarianWindowComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
