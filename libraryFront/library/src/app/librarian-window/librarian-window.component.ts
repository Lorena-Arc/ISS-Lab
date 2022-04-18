import { Component, OnInit } from '@angular/core';
import {LibrarianService} from "../services/librarian.service";
import {BookModel, BookStatus} from "../models/book.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {interval, Subscription, timer} from "rxjs";

@Component({
  selector: 'app-librarian-window',
  templateUrl: './librarian-window.component.html',
  styleUrls: ['./librarian-window.component.scss']
})
export class LibrarianWindowComponent implements OnInit {

  books: BookModel[] = [];
  booksForm: any;
  private updateSubscription: Subscription | undefined;

  constructor(private service: LibrarianService, private builder: FormBuilder) { }

  ngOnInit(): void {

    this.getBooks();
    this.booksForm = this.builder.group({
      id: '',
      titlu: '',
      autor: '',
      statusCarte: ''
    })

    this.updateSubscription = timer(0,5000).subscribe(val => {
      this.getBooks();
    });
  }

  getBooks() {
    this.service.getBooks().subscribe(data => {
      this.books = data;
    })
  }

  addBook() {
    this.service.addBook(this.booksForm.getRawValue()).subscribe((data) => {
      this.getBooks();
    })
  }

  deleteBook() {
    this.service.deleteBook(this.booksForm.getRawValue()).subscribe(data => {
      this.getBooks();
    })
  }

  modifyBook() {
    this.service.modifyBook(this.booksForm.getRawValue()).subscribe(data => {
      this.getBooks();
    })
  }
}
