import {Component, OnDestroy, OnInit} from '@angular/core';
import {BookModel} from "../models/book.model";
import {SubscriberService} from "../services/subscriber.service";
import {Subscription, timer} from "rxjs";
import {NotificationService} from "../services/notification.service";

@Component({
  selector: 'app-subscriber-window',
  templateUrl: './subscriber-window.component.html',
  styleUrls: ['./subscriber-window.component.scss']
})
export class SubscriberWindowComponent implements OnInit, OnDestroy {

  books: BookModel[] | undefined;
  private updateSubscription: Subscription | undefined;

  constructor(private service: SubscriberService, private notify: NotificationService) { }

  ngOnInit(): void {
    this.getBooks();

    this.updateSubscription = timer(0,5000).subscribe(_val => {
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

  ngOnDestroy(): void {
    this.updateSubscription?.unsubscribe();
  }
}
