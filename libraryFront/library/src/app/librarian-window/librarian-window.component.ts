import {Component, OnDestroy, OnInit} from '@angular/core';
import {LibrarianService} from "../services/librarian.service";
import {BookModel} from "../models/book.model";
import {FormBuilder} from "@angular/forms";
import {Subscription, timer} from "rxjs";
import {NotificationService} from "../services/notification.service";

@Component({
  selector: 'app-librarian-window',
  templateUrl: './librarian-window.component.html',
  styleUrls: ['./librarian-window.component.scss']
})
export class LibrarianWindowComponent implements OnInit, OnDestroy {

  books: BookModel[] = [];
  booksForm: any;
  returnForm: any;
  private updateSubscription: Subscription | undefined;

  constructor(private service: LibrarianService, private builder: FormBuilder, private notify: NotificationService) { }

  ngOnInit(): void {

    this.getBooks();
    this.booksForm = this.builder.group({
      id: '',
      titlu: '',
      autor: '',
      statusCarte: ''
    });

    this.returnForm = this.builder.group({
      id: '',
    })

    this.updateSubscription = timer(0,5000).subscribe(val => {
      this.getBooks();
    });
  }

  getBooks() {
    this.service.getBooks().subscribe(data => {
      this.books = data;
    }, (error) => {
      this.notify.showError();
    })
  }

  addBook() {
    this.service.addBook(this.booksForm.getRawValue()).subscribe((data) => {
      this.getBooks();
      this.notify.showSuccess();
    }, (error) => {
      this.notify.showError();
    })
  }

  deleteBook() {
    this.service.deleteBook(this.booksForm.getRawValue()).subscribe(data => {
      this.getBooks();
      this.notify.showSuccess();
    }, (error) => {
      this.notify.showError();
    })
  }

  modifyBook() {
    this.service.modifyBook(this.booksForm.getRawValue()).subscribe(data => {
      this.getBooks();
      this.notify.showSuccess();
    }, (error) => {
      this.notify.showError();
    })
  }

  returnBook() {
    if (this.returnForm.getRawValue().id) {
      this.service.returnBook(this.returnForm.getRawValue().id).subscribe((data) => {
        this.getBooks();
        this.notify.showSuccess()
      }, (error) => {
        this.notify.showError();
      })
    }
  }

  ngOnDestroy() {
    this.updateSubscription?.unsubscribe();
  }
}
